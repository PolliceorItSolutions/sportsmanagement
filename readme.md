
# Sports Management System

A Jakarta EE-based sports management system integrated with Firebase Authentication and AWS Secrets Manager.

## 🚀 Features

- User Authentication via Firebase
- Role-based Authorization
- Secure Secrets Management via AWS
- RESTful API endpoints
- Database integration with JPA

## 🛠 Tech Stack

- Java 17
- Spring Boot with Jakarta EE
- Spring Data JPA
- Firebase Authentication
- AWS Secrets Manager
- Lombok
- PostgreSQL (assumed from JPA configuration)

## 📋 Prerequisites

- JDK 17 or later
- Maven
- Firebase Account and Project
- AWS Account (optional, for Secrets Manager)
- PostgreSQL Database

## ⚙️ Configuration

### Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Download your Firebase service account key JSON file (check section on how to download the firbase json file)
3. Place it at `src/main/resources/firebase-service-account.json`
4. Do not push this file to git.

### Database Configuration

Configure database properties in `application.properties` or via AWS Secrets Manager:

### environment properties setup
create a .env file at project level. 
add an entry FIREBASE_API_KEY=<FIREBASE_API_KEY>
do not push this file into git. 


### Download Firebasejson file
1️⃣ Go to Firebase Console

Open Firebase Console

Select your project.

2️⃣ Navigate to Project Settings

Click the gear icon ⚙️ next to “Project Overview”

Select Project settings

Go to the Service accounts tab.

3️⃣ Generate a New Private Key

In the Firebase Admin SDK section, click Generate new private key

Confirm by clicking Generate key

This will download a JSON file to your computer.
Important: Store it securely (don’t commit it to Git or share publicly).