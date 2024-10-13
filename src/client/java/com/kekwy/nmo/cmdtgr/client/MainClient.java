package com.kekwy.nmo.cmdtgr.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MainClient implements ClientModInitializer {

    private static final Identifier CHANNEL_ID = new Identifier("cmdtgr", "channel");

    @Override
    public void onInitializeClient() {
        // 注册客户端接收来自服务器的消息
        ClientPlayNetworking.registerGlobalReceiver(CHANNEL_ID, (client, handler, buf, responseSender) -> {
            // 从 buf 读取服务器发送的数据
            String receivedMessage = buf.readString(32767);

            // 在客户端主线程中处理数据
            client.execute(() -> {
                // 执行客户端的操作，比如显示聊天消息
                client.player.sendMessage(Text.of("Server says: " + receivedMessage), false);

                // 你也可以在这里编写自定义逻辑来响应该请求
            });
        });
    }
}
