package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        Calculator calculator = new Calculator();
        // Kubernetes sets HOSTNAME inside the container to the pod name.
        String pod = System.getenv().getOrDefault("HOSTNAME", "unknown");

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", exchange -> {
            String text = "Hello from the CI/CD pipeline! 2 + 3 = " + calculator.add(2, 3)
                    + " | pod: " + pod + "\n";
            byte[] body = text.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(body);
            }
        });
        server.start();
        System.out.println("Server started on port " + port);
    }
}
