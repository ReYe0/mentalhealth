package com.study.front.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.study.front.dao.UserDao;
import com.study.front.entities.SocketMsg;
import com.study.front.entities.Staff;
import com.study.front.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/4/20 13:09
 */
@ServerEndpoint(value = "/websocket/{id}")
@Component
public class WebSocketService {

    public static UserDao userDao;

    private String nickname;
    private Session session;

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<WebSocketService>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    //用来记录sessionId和该session进行绑定
    private static Map<String, Session> map = new HashMap<String, Session>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        String[] array = id.split(",");
        String aisle;
        if (array[0].equals("a")){
            User user = userDao.getUserById(Integer.valueOf(array[1]));
            this.nickname = user.getName();
            aisle = "a"+user.getId();
        }else {
            Staff staff = userDao.findByStaffId(Integer.valueOf(array[2]));
            this.nickname = staff.getStaffName();
            aisle = "b"+staff.getId();
        }
//        if(!map.containsKey(aisle)) {
            Map<String, Object> message = new HashMap<String, Object>();
            this.session = session;
//        this.nickname = user.getName();
            map.put(aisle, session);
            webSocketSet.add(this);//加入set中
            System.out.println("有新连接加入:" + nickname + ",当前在线人数为" + webSocketSet.size());
            //this.session.getAsyncRemote().sendText("恭喜" + nickname + "成功连接上WebSocket(其频道号：" + session.getId() + ")-->当前在线人数为：" + webSocketSet.size());
            message.put("type", 0); //消息类型，0-连接成功，1-用户消息
            message.put("people", webSocketSet.size()); //在线人数
            message.put("name", nickname); //昵称
            message.put("aisle", aisle); //频道号
            this.session.getAsyncRemote().sendText(new Gson().toJson(message));
//        }else{
//            Map<String, Object> message = new HashMap<String, Object>();
//            this.session = map.get(aisle);
////        this.nickname = user.getName();
////            map.put(aisle, session);
////            webSocketSet.add(this);//加入set中
////            System.out.println("有新连接加入:" + nickname + ",当前在线人数为" + webSocketSet.size());
////            Session session1 = map.get(aisle);
//            //this.session.getAsyncRemote().sendText("恭喜" + nickname + "成功连接上WebSocket(其频道号：" + session.getId() + ")-->当前在线人数为：" + webSocketSet.size());
//            message.put("type", 0); //消息类型，0-连接成功，1-用户消息
//            message.put("people", webSocketSet.size()); //在线人数
//            message.put("name", nickname); //昵称
//            message.put("aisle", aisle); //频道号
//            boolean open = this.session.isOpen();
////            this.session.
//            this.session.getAsyncRemote().sendText(new Gson().toJson(message));
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("id") String id) {
        String[] array = id.split(",");
        String aisle;
        if (array[0].equals("a")){
            User user = userDao.getUserById(Integer.valueOf(array[1]));
            this.nickname = user.getName();
            aisle = "a"+user.getId();
        }else {
            Staff staff = userDao.findByStaffId(Integer.valueOf(array[2]));
            this.nickname = staff.getStaffName();
            aisle = "b"+staff.getId();
        }
        String[] messageArray = message.split("\"");
        Integer integer = userDao.insertMsg(Integer.valueOf(array[1]), Integer.valueOf(array[2]), messageArray[3], nickname, messageArray[10].substring(1,2));
//        nickname = user.getName();
        System.out.println("来自客户端的消息-->" + nickname + ": " + message);

        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象，
        // 然后通过socketMsg的type进行判断是单聊还是群聊，进行相应的处理:
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;

        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if (socketMsg.getType() == 1) {
                //单聊.需要找到发送者和接受者.
                socketMsg.setFromUser(aisle);//发送者.
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                //发送给接受者.
                if (toSession != null) {
                    //发送给发送者.
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("type", 1);
                    m.put("name", nickname);
                    m.put("msg", socketMsg.getMsg());
                    fromSession.getAsyncRemote().sendText(new Gson().toJson(m));
                    toSession.getAsyncRemote().sendText(new Gson().toJson(m));
                } else {
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(new Gson().toJson("系统消息：对方不在线或者您输入的频道号不对"));
                }
            } else {
                //群发消息
                broadcast(nickname + ": " + socketMsg.getMsg());
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public void broadcast(String message) {
        for (WebSocketService item : webSocketSet) {
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }

    }
}
