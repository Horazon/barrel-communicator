package pl.horazon.server.handle;

import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.domain.DirectChatMsg;
import pl.horazon.barrel.common.pojo.enums.MsgDest;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.server.SocketServer;

public class NewMsgHandler {

    public static void handleNewMsg(GroupChatMsg msg){

        Logger.info("New message. Details " + msg);

        if(MsgDest.ALL.equals(msg.getDest())){
            Logger.info("Distributing to all");

            SocketServer.sockets.stream()
                    //.filter(obj -> !Objects.equals(obj, this))
                    .forEach(socketRequest -> {
                        socketRequest.send(msg);
                    });
        }
    }

    public static void handleDirectMsg(DirectChatMsg msg) {
        SocketServer.sockets.stream()
                //.filter(obj -> !Objects.equals(obj, this))
                .forEach(socketRequest -> {
                    socketRequest.send(msg);
                });
    }
}
