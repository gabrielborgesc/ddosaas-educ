# DDoSaaS Simulation 🧪💥

**DDoSaaS** (Distributed Denial-of-Service as a Service) is a controlled simulation platform designed for stress-testing, research, and educational purposes. It provides an API and a web-based panel to configure and launch synthetic DDoS-style traffic against specified URLs under safe, authorized conditions.

> ⚠️ This tool is intended **only for legal, educational, and testing purposes** within isolated or authorized environments. **Do not use** against public or third-party services without explicit permission.

---

## 🚀 Features

- 🔌 **RESTful API** to launch simulated DDoS attacks
- 🌐 **Web UI** for manual configuration
- ⚙️ Configurable number of threads and source IPs
- 🔐 Token-based access control
- 🐳 Dockerized for easy deployment

---

## 📸 Web Interface

![Web UI Screenshot](./aeeebdc7-f8b9-46b6-864d-83e089297b82.png)

---

## 🛠️ How It Works

1. A client sends a request to the API with:
   - Target URL
   - Access token
   - Number of threads
   - Number of simulated IPs

2. The system dispatches worker threads to generate concurrent requests toward the target.

3. All operations are logged for monitoring and analysis.

---

## 📦 Installation

### Requirements

- Docker
- Docker Compose

### Clone the Repository

```bash
git clone https://github.com/your-username/ddosaas.git
cd ddosaas
