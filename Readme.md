# ⚡ TPCODL Spot Billing App

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/firebase-%23039BE5.svg?style=for-the-badge&logo=firebase)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

**A modern Android application for efficient electricity bill management and consumer data retrieval**

[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](https://github.com/)
[![License](https://img.shields.io/badge/License-Proprietary-red.svg)](LICENSE)

</div>

---

## 📱 Overview

The TPCODL Spot Billing App is a comprehensive mobile solution designed for electricity billing management. Built with cutting-edge Android technologies, this app provides seamless access to consumer billing information, real-time notifications, and secure user authentication.

<div align="center">
  <img src="https://img.icons8.com/fluency/96/lightning-bolt.png" alt="Lightning" width="60"/>
  <img src="https://img.icons8.com/fluency/96/mobile-payment.png" alt="Mobile Payment" width="60"/>

  <img src="https://img.icons8.com/fluency/96/database.png" alt="Database" width="60"/>
</div>

---

## ✨ Features

### 🔐 **Authentication System**
<img src="https://img.icons8.com/fluency/48/security-checked.png" alt="Security" align="left" width="40" style="margin-right: 10px;"/>

- **Multi-Channel Login**: Secure login using email or phone number
- **API Integration**: Robust authentication through dedicated login API
- **User Session Management**: Persistent login state with secure token handling

### 🏠 **Home Dashboard**
<img src="https://img.icons8.com/fluency/48/dashboard-layout.png" alt="Dashboard" align="left" width="40" style="margin-right: 10px;"/>

- **Intuitive Interface**: Clean and user-friendly home screen
- **Quick Access**: Direct navigation to billing features
- **Real-time Updates**: Live data synchronization

### 💰 **Spot Billing Management**
<img src="https://img.icons8.com/fluency/48/bill.png" alt="Bill" align="left" width="40" style="margin-right: 10px;"/>

- **Consumer Data Retrieval**: Fetch detailed consumer information
- **Multiple Input Methods**: Support for various billing parameters
    - Consumer Number
    - Reference Number
    - Bill Type
    - User ID
- **API-Driven**: Seamless integration with TPCODL spot billing API

### 🔔 **Push Notifications**
<img src="https://img.icons8.com/fluency/48/appointment-reminders.png" alt="Notifications" align="left" width="40" style="margin-right: 10px;"/>

- **Real-time Alerts**: Instant notifications for billing updates
- **Firebase Integration**: Reliable push notification service
- **Customizable**: Tailored notification preferences

---

## 🛠️ Technology Stack

<div align="center">

### **Core Technologies**

<img src="https://img.icons8.com/color/96/kotlin.png" alt="Kotlin" width="80"/>
<img src="https://img.icons8.com/color/96/android-studio--v3.png" alt="Android Studio" width="80"/>
<img src="https://img.icons8.com/color/96/firebase.png" alt="Firebase" width="80"/>
<img src="https://img.icons8.com/fluency/96/api.png" alt="API" width="80"/>

</div>

### **Architecture & Languages**
- ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=flat-square&logo=kotlin&logoColor=white) **Language**: Kotlin
- ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white) **UI Framework**: Jetpack Compose
- ![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue?style=flat-square) **Architecture**: MVVM (Model-View-ViewModel)
- ![Room](https://img.shields.io/badge/Database-Room-green?style=flat-square) **Database**: Room Database

### **Key Libraries & Dependencies**

#### **🎨 UI & Navigation**
```kotlin
// Jetpack Compose
implementation(platform(libs.androidx.compose.bom))
implementation(libs.androidx.material3)        // Material Design 3
implementation(libs.androidx.ui)               // Core UI components

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")
```

#### **🌐 Networking**
```kotlin
// HTTP Client - Retrofit & OkHttp
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
```

#### **🗄️ Database**
```kotlin
// Room Database
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")
```

#### **🔥 Firebase Services**
```kotlin
// Firebase Platform
implementation(platform("com.google.firebase:firebase-bom:33.14.0"))
implementation("com.google.firebase:firebase-analytics")
implementation("com.google.firebase:firebase-messaging:23.4.0")
```

#### **💾 Data Management**
```kotlin
// Data Storage & Serialization
implementation("androidx.datastore:datastore-preferences:1.0.0")
implementation("com.google.code.gson:gson:2.10.1")
```

---

## 📋 Requirements

<div align="center">

![Min SDK](https://img.shields.io/badge/Min%20SDK-24-green?style=for-the-badge&logo=android)
![Target SDK](https://img.shields.io/badge/Target%20SDK-35-blue?style=for-the-badge&logo=android)
![Compile SDK](https://img.shields.io/badge/Compile%20SDK-35-orange?style=for-the-badge&logo=android)

</div>

### **System Requirements**
- <img src="https://img.icons8.com/fluency/24/android-os.png" alt="Android"/> **Minimum SDK**: Android 7.0 (API level 24)
- <img src="https://img.icons8.com/fluency/24/android-os.png" alt="Android"/> **Target SDK**: Android 15 (API level 35) 
- <img src="https://img.icons8.com/fluency/24/android-os.png" alt="Android"/> **Compile SDK**: Android 15 (API level 35)

### **Development Environment**
- <img src="https://img.icons8.com/color/24/android-studio--v3.png" alt="Android Studio"/> **Android Studio**: Arctic Fox or later
- <img src="https://img.icons8.com/color/24/kotlin.png" alt="Kotlin"/> **Kotlin**: 1.9.0+
- <img src="https://img.icons8.com/color/24/java-coffee-cup-logo--v1.png" alt="Java"/> **Java**: Version 11
- <img src="https://img.icons8.com/fluency/24/software-installer.png" alt="Gradle"/> **Gradle**: 8.0+

---

## 🚀 Getting Started

### **Prerequisites**
<img src="https://img.icons8.com/fluency/32/checklist.png" alt="Checklist"/>

1. <img src="https://img.icons8.com/color/20/android-studio--v3.png" alt="Android Studio"/> Android Studio installed
2. <img src="https://img.icons8.com/color/20/firebase.png" alt="Firebase"/> Firebase project setup
3. <img src="https://img.icons8.com/fluency/20/api.png" alt="API"/> TPCODL API access credentials

### **Installation Steps**

1. **🔄 Clone the Repository**
   ```bash
   git clone [repository-url]
   cd tpcodl-spot-billing
   ```

2. **🔥 Firebase Configuration**
   ```bash
   # Add google-services.json to the app/ directory
   # Configure Firebase Analytics and Messaging
   ```

3. **⚙️ API Configuration**
   ```bash
   # Update API endpoints in the configuration files
   # Add authentication credentials
   ```

4. **🔨 Build the Project**
   ```bash
   ./gradlew build
   ```

5. **▶️ Run the Application**
   ```bash
   ./gradlew installDebug
   ```

---

## 📱 App Flow

<div align="center">

```mermaid
graph TD
    A[<img src='https://img.icons8.com/fluency/48/mobile-phone.png'/><br/>App Launch] --> B[<img src='https://img.icons8.com/fluency/48/loading.png'/><br/>Splash Screen]
    B --> C[<img src='https://img.icons8.com/fluency/48/login.png'/><br/>Login Page<br/>Email/Phone]
    C --> D[<img src='https://img.icons8.com/fluency/48/dashboard-layout.png'/><br/>Home Dashboard]
    D --> E[<img src='https://img.icons8.com/fluency/48/bill.png'/><br/>Spot Billing Input]
    E --> F[<img src='https://img.icons8.com/fluency/48/combo-chart.png'/><br/>Consumer Data Display]
    
    style A fill:#e1f5fe
    style B fill:#f3e5f5
    style C fill:#fff3e0
    style D fill:#e8f5e8
    style E fill:#fff8e1
    style F fill:#fce4ec
```

</div>

---

## 🏗️ Architecture

<div align="center">
  <img src="https://img.icons8.com/fluency/96/module.png" alt="Architecture" width="80"/>
</div>

The app follows **MVVM (Model-View-ViewModel)** architecture pattern:

- **📊 Model**: Data layer with Room database and API services
- **🎨 View**: UI layer built with Jetpack Compose
- **🔄 ViewModel**: Business logic and state management

---

## 🔧 Configuration

### **🌐 API Integration**
- <img src="https://img.icons8.com/fluency/20/login.png" alt="Login"/> **Login API**: User authentication service
- <img src="https://img.icons8.com/fluency/20/bill.png" alt="Billing"/> **Spot Billing API**: Consumer data retrieval service

### **🗄️ Database Schema**
- <img src="https://img.icons8.com/fluency/20/user.png" alt="User"/> User session data
- <img src="https://img.icons8.com/fluency/20/customer.png" alt="Consumer"/> Consumer information cache
- <img src="https://img.icons8.com/fluency/20/time-machine.png" alt="History"/> Billing history

### **🔔 Notification Setup**
- <img src="https://img.icons8.com/color/20/firebase.png" alt="Firebase"/> Firebase Cloud Messaging configuration
- <img src="https://img.icons8.com/fluency/20/notification.png" alt="Notification"/> Custom notification channels
- <img src="https://img.icons8.com/fluency/20/services.png" alt="Service"/> Background service integration

---

## 🧪 Testing

<div align="center">

![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Espresso](https://img.shields.io/badge/Espresso-6DB33F?style=for-the-badge&logo=android&logoColor=white)

</div>

The app includes comprehensive testing:

```kotlin
// Unit Tests
testImplementation("junit:junit:4.13.2")

// Android Instrumentation Tests  
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// Jetpack Compose Tests
androidTestImplementation("androidx.compose.ui:ui-test-junit4")
```

---

## 📦 Build Configuration

<div align="center">

![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Version](https://img.shields.io/badge/Version-1.0-blue?style=for-the-badge)

</div>

- **📱 Application ID**: `com.example.kotlincourse`
- **🔢 Version Code**: 1
- **📝 Version Name**: 1.0
- **🔨 Build Tools**: Android Gradle Plugin 8.0+

---

## 🔒 Security Features

<div align="center">
  <img src="https://img.icons8.com/fluency/64/security-shield-green.png" alt="Security"/>
</div>

- <img src="https://img.icons8.com/fluency/20/ssl-security.png" alt="HTTPS"/> Secure API communication with HTTPS
- <img src="https://img.icons8.com/fluency/20/key.png" alt="Token"/> Token-based authentication
- <img src="https://img.icons8.com/fluency/20/encrypt.png" alt="Encryption"/> Data encryption for sensitive information
- <img src="https://img.icons8.com/fluency/20/database-protected.png" alt="Secure Storage"/> Secure local storage using DataStore

---

## 🚀 Performance Optimizations

<div align="center">
  <img src="https://img.icons8.com/fluency/64/speed.png" alt="Performance"/>
</div>

- **🎨 Efficient UI**: Jetpack Compose for smooth animations
- **🌐 Network Optimization**: Retrofit with OkHttp interceptors
- **🗄️ Database Optimization**: Room with coroutines support
- **🧠 Memory Management**: Lifecycle-aware components

---

## 📞 Support & Contact

<div align="center">

![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)
![Support](https://img.shields.io/badge/Support-Available-green?style=for-the-badge)

For technical support or inquiries related to the TPCODL Spot Billing App, please contact the development team.

</div>

---

## 📄 License

<div align="center">

![License](https://img.shields.io/badge/License-Proprietary-red?style=for-the-badge)

This project is proprietary software developed for TPCODL.

</div>

---

<div align="center">

**Built with ❤️ for TPCODL**

*Empowering efficient electricity billing management through innovative mobile technology*

<img src="https://img.icons8.com/fluency/48/lightning-bolt.png" alt="Lightning"/>

<img src="https://img.icons8.com/fluency/48/innovation.png" alt="Innovation"/>

---

![Made with Love](https://img.shields.io/badge/Made%20with-❤️-red?style=for-the-badge)
![Android](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?style=for-the-badge&logo=kotlin)

</div>