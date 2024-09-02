package helpers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.nio.CharBuffer;

/**
 * Author @ChattGPT
 */
public class Argon2Helper {
    private static final Argon2 argon2 = Argon2Factory.create();

    // Method to hash the password
    public static String hashPassword(String password) {
        int iterations = 2;  // Recommended number of iterations
        int memory = 65536;  // Memory usage in KB
        int parallelism = 1; // Number of threads to use

        // Hash the password
        return argon2.hash(iterations, memory, parallelism, password);
    }

    // Adjusted method to return a CharBuffer
    public static CharBuffer hashPasswordChar(String password) {
        int iterations = 2;  // Recommended number of iterations
        int memory = 65536;  // Memory usage in KB
        int parallelism = 1; // Number of threads to use

        // Hash the password and wrap the result in a CharBuffer
        return CharBuffer.wrap(argon2.hash(iterations, memory, parallelism, password).toCharArray());
    }


    // Method to verify the password
    public static boolean verifyPassword(String hash, String password) {
        return argon2.verify(hash, password);
    }
}