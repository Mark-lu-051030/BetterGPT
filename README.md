# BetterGPT
# Overview
BetterGPT is a subscription-based chatbot leveraging OpenAI’s GPT-4. It allows users to interact with GPT-4 through a user-friendly interface with various subscription tiers. Users can manage their subscriptions, track their usage, and save conversation history. The application also includes a Java quiz system for users to practice programming, with a point-based system to enhance engagement.
<br>Project Blue-Print: https://docs.google.com/document/d/1A3Ebp-N7RxdrXN056FfhB-pLZrnz_WPkpwETsMoy8yQ/edit?usp=sharing
# Features
- User Registration and Login (Phase 1)
- Subscription Management
- Usage Tracking
- Conversation History
- Chatbot Interaction (Phase 1)
- UI with Dark Mode (Phase 1 have partially finished)
- Java Quiz System
# Table of Contents
- [Prerequisites](#prerequisites)
- [Architecture](#architecture)
- [Entities](#entities)
- [Classes and Functions](#classes-and-functions)
- [Testing](#testing)
- [Contribution](#contribution)


## Prerequisites

- Java 11 or higher
- Maven

## Architecture

BetterGPT follows SOLID principles and Clean Architecture, ensuring maintainability and scalability. The project is structured into several packages:

- `app`: Contains the main entry point of the application.
- `data_access`: Handles data operations such as database interactions.
- `entity`: Defines core business entities.
- `interface_adapter`: Manages user interface controllers and adapters.
- `use_case`: Contains the business logic of the application.
- `view`: Manages the graphical user interface components.

## Entities

### User (Phase 1)
Represents a user in the system. Contains attributes like username, password, email, and subscription details.

### Subscription (Phase 1)
Represents a subscription plan for a user. Contains attributes like subscription type, start date, end date, and usage limits.

### Conversation (Phase 1)
Represents a conversation between the user and the chatbot. Contains attributes like conversation ID, user ID, and messages.

### Message

### Quiz

### Question

### Answer

### Leaderboard (potential)

## Classes and Functions

### app

#### Main.java (Phase 1)
The entry point of the application. Initializes the application and starts the main program loop.

### data_access

#### DataHandler.java (Phase 1)
Handle the format of how user data will be sent to OpenAI.

#### GptApiClient.java (Phase 1)
Communicates with the GPT-3.5 API to send user queries and receive responses.

#### PasswordResetService.java (Phase 1)
Manages password reset operations for users.

#### SQLiteUserRepository.java (Phase 1)
Implements user repository using SQLite database.

### entity

#### Conversation.java
Defines the Conversation entity with attributes and methods related to a conversation.

#### Subscription.java (Phase 1)
Defines the Subscription entity with attributes and methods related to user subscriptions.

#### User.java (Phase 1)
Defines the User entity with attributes and methods related to user information.

### interface_adapter

#### ChatController.java (Phase 1)
Manages chat interactions between the user and the chatbot.

#### SignInController.java

#### SignUpController.java

### use_case

#### ChatService.java (Phase 1)
Contains business logic for handling chat operations.

#### PasswordRequest.java (Phase 1)
Contains business logic for handling password reset requests.

#### SignInService.java (Phase 1)
Contains business logic for handling user sign-ins.

#### SignUpService.java (Phase 1)
Contains business logic for handling user sign-ups.

### view

#### Util

#### ChatArea.java (Phase 1)
Defines the chat area component in the UI.

#### ChatBox.java (Phase 1)
Defines the chat box component where users input their messages.

#### ChatPanel.java (Phase 1)
Manages the layout and behavior of the chat panel.

#### GPTNewChatButton.java (Phase 1)
Defines the button for starting a new chat.

#### Sidebar.java (Phase 1)
Manages the sidebar UI component.

#### ViewManager.java (Phase 1)
Manages the overall view and layout of the application

## Testing
### Test Coverage
- Subscription
- User
- ChatService
- PasswordRequest
- SignInService

## Contribution
Everyone in the group contributed and put effort into the project.

