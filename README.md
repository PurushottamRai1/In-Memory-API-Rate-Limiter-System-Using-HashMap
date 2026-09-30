# In-Memory API Access Control System

A simple, interactive Java console application that simulates an API Gateway. It tracks API key usage, allows access, and blocks requests that exceed their monthly quota limits.

## How to Run the Program

Since this project consists of a single `Main.java` file, you can run it instantly using any online Java compiler or local terminal.

### Option 1: Using an Online Compiler (Easiest)
1. Copy the entire source code from `Main.java`.
2. Paste it into an online Java compiler like [JDoodle](https://jdoodle.com) or [OnlineGDB](https://onlinegdb.com).
3. Click **Run**.

### Option 2: Using the Command Line / Terminal
If you have Java installed on your computer, open your terminal in the directory where `Main.java` is saved and run:
```bash
javac Main.java
java Main
```

## Features Demonstrated
* **O(1) Token Verification:** Uses a `HashMap` registry for lightning-fast API key lookups.
* **Rate Limiting Simulation:** Tracks active vs. blocked statuses and mimics `401 Unauthorized` and `429 Too Many Requests` HTTP status codes.
* **Terminal UI Polish:** Uses standard ANSI escape codes to render colored statuses (Green for success, Red for errors) in compatible terminals.
