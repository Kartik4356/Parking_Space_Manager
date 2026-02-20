# Parking Space Manager 🚗☁️

A full-stack, cloud-native web application designed to efficiently manage parking lot operations. This system streamlines parking space allocation, tracks vehicle entries and exits in real-time, manages employee shifts, and enforces role-based access control.



## 🛠️ Tech Stack & Architecture

**Cloud Infrastructure:**
* **AWS EC2:** Hosts the Java application and Apache Tomcat web server for high availability and scalability.
* **AWS RDS:** Managed relational database service ensuring secure, automated, and reliable data storage.

**Application Layer:**
* **Backend:** Java (Servlets, JDBC) for robust server-side business logic and secure database interactions.
* **Frontend:** JSP (JavaServer Pages), HTML5, and CSS3 for a dynamic, responsive, and intuitive user interface.
* **Database:** SQL (MySQL/Oracle) with optimized schemas for tracking vehicles, employees, and session logs.

## ✨ Key Features

* **Real-Time Dashboard:** Dynamically calculates and displays the live capacity of Two-Wheeler and Four-Wheeler vehicles in the parking lot.
* **Automated Vehicle Logging:** Securely captures vehicle entry/exit times, owner contact details, and automatically generates accurate timestamps.
* **Employee Management & Security:** Tracks employee shifts, monitors login/logout sessions, and records the exact number of entries processed per employee session.
* **Cloud-First Security:** Database credentials and sensitive configurations are strictly decoupled from the codebase using secure server environment variables.

## 🚀 Cloud Deployment (AWS)

This application is engineered for deployment on Amazon Web Services. The local development environment has been fully migrated to a cloud architecture:

1. **Database:** The relational database is hosted on **Amazon RDS**, replacing local `localhost` connections with secure cloud endpoints.
2. **Web Server:** The compiled `.war` file is deployed onto an **Amazon EC2** Ubuntu instance running Apache Tomcat 9.
3. **Infrastructure as Code:** The repository includes an `ec2_setup.sh` script to automate the configuration of the Linux environment, install dependencies (Java, Tomcat), and securely inject AWS RDS credentials into the server environment.

## ⚙️ Local Setup & Installation

To run this project locally for development or testing:

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Kartik4356/Parking_Space_Manager.git](https://github.com/Kartik4356/Parking_Space_Manager.git)
