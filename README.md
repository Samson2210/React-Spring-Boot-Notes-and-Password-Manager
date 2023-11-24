# Notes and Password Manager Project

## Overview

This project is a Notes and Password Manager application that provides users with a secure platform to manage their notes and passwords. The application is built using React.js for the front end, Spring Boot for the backend, and MongoDB Atlas for storage. Security is ensured through Spring Security with JWT authentication. Access the application in your browser: [https://secretkeeperx.onrender.com/](https://secretkeeperx.onrender.com/)

## Features

- **Secure Authentication:** Utilizes Spring Security with JWT authentication to ensure secure access to the application.

- **Notes Management:**
  - **Create:** Users can create and store notes securely.
  - **Update:** The application allows users to update their existing notes.
  - **Delete:** Users can delete notes they no longer need.

- **Password Management:**
  - **Secure Storage:** Passwords are securely stored in the database.
  - **Update:** Users can update their stored passwords.
  - **Deletion:** Passwords can be deleted when no longer needed.

## Technology Stack

- **Frontend:**
  - **React.js:** A JavaScript library for building user interfaces.

- **Backend:**
  - **Spring Boot:** A Java-based framework for building robust and scalable backend applications.

- **Database:**
  - **MongoDB Atlas:** A cloud-based NoSQL database for storing application data.

- **Authentication:**
  - **Spring Security:** Provides authentication and authorization support.
  - **JWT (JSON Web Token):** Used for secure authentication.

- **Password Security:**
  - **Encryption:** Passwords are encrypted before being stored in the database.

- **Hosting:**
  - **Render:** The application is hosted on Render for seamless deployment.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Node.js for running the React.js frontend.
- Java and Maven for building and running the Spring Boot backend.
- MongoDB Atlas account for database access.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/notes-password-manager.git

### Frontend Setup:

    cd notes-password-manager/frontend
    npm install
    npm start

### Backend Setup:

    cd notes-password-manager/backend
    mvn spring-boot:run
