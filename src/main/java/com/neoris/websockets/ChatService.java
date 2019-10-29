package com.neoris.websockets;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatService.
 */
@ServerEndpoint(value = "/websocket/chat")
public class ChatService {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(ChatService.class);

    /** The Constant GUEST_PREFIX. */
    private static final String GUEST_PREFIX = "Guest";
    
    /** The Constant connectionIds. */
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    
    /** The Constant connections. */
    private static final Set<ChatService> connections
            = new CopyOnWriteArraySet<>();

    /** The nickname. */
    private final String nickname;
    
    /** The session. */
    private Session session;

    /**
     * Instantiates a new chat service.
     * 
     * @author marco.slehiman
     */
    public ChatService() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    /**
     * Start.
     *
     * @param session the session
     */
    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(message);
    }

    /**
     * End.
     *
     * @param session the session
     */
    @OnClose
    public void end(Session session) {
        connections.remove(this);
        String message = String.format("* %s %s",
                nickname, "has disconnected.");
        broadcast(message);
    }

    /**
     * Incoming.
     *
     * @param message the message
     */
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, HTMLFilter.filter(message.toString()));
        broadcast(filteredMessage);
    }

    /**
     * On error.
     *
     * @param t the t
     * @throws Throwable the throwable
     */
    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }

    /**
     * Broadcast.
     *
     * @param msg the msg
     */
    private static void broadcast(String msg) {
        for (ChatService client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(message);
            }
        }
    }
}
