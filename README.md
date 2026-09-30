# In-Memory API Access Control System

A console-based Java application that simulates an **API gateway rate-limiting and access control feature**. It dynamically tracks client API requests in-memory using an efficient $O(1)$ constant-time lookup and enforces usage caps.

## 🚀 Features
* **In-Memory Tracking:** Simulates real-time data persistence using a structured `HashMap` registry.
* **Rate Limiting (HTTP 429):** Rejects requests automatically once a client's specific monthly limit is reached.
* **Authentication Validation (HTTP 401):** Validates tokens and flags unauthorized access keys.
* **Polished Interactive Console:** Includes a snapshot dashboard with color-coded ANSI formatting highlighting active and blocked keys.

---

## 📁 Repository Structure
```text
.
├── src/
│   └── Main.java       # Contains the core execution logic and model class
├── .gitignore          # Excludes compiled build components from tracking
└── README.md           # Project documentation and guide
```

---

## 💻 Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher (Recommended: JDK 17 / 21)
* **Terminal/Command Prompt**

---

## 🛠️ How to Compile and Run

Follow these steps to pull down and run the project manually from a local console window.

### Step 1: Compile the Source Code
Navigate to your project root folder and execute the Java compiler:
```bash
javac src/Main.java
```
*(This command builds the `.java` files into `.class` bytecode instructions inside your workspace).*

### Step 2: Run the Executable
Launch the interactive CLI dashboard interface using the JVM:
```bash
java -cp src Main
```

---

## ⌨️ Command Console Cheat-Sheet
When running, you can supply one of the pre-loaded keys into the interface to test state mutation constraints:

| Pre-Loaded Sample Token | Scenario Handled | Initial Hit Status | Expected Response Status |
| :--- | :--- | :--- | :--- |
| `DEV-ALPHA-101` | Safe Key | `0 / 5` Hits | `200 Success` (Usage increments) |
| `DEV-DELTA-404` | Exhausted Key | `5 / 5` Hits | `429 Blocked` (Request denied) |
| `UNKNOWN-TOKEN` | Bad Request | Non-existent | `401 Unauthorized` |

*   **`print`**: Refreshes and prints the real-time API keys status matrix map.
*   **`exit`**: Smoothly terminates the continuous interactive processing engine loop.
