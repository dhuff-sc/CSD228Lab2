# Messenger Project ADR - Data Types
Date: 2024-02-14

## Status

Proposed, Data Layer underway

## Content

Project requires creation of the data layer as per following requirements:
- Store basic user data as a participant of a conversation
- Send messages when offline and have those send when connected to a wireless connection again
- See a history of a conversation
- Have a conversation with either a single person or a group conversation
- See when a user sent a message
- Toggle read receipts
- Know the status of the message in a conversation. eg. Sent, Delivered, Read, Failed to send etc
- Delete messages from a conversation
- Send mixed media messages

## Solutions

### Data Types

User data storage can be local, and similar methods can be implemented to create data types of Users,
Messages, and the conversation as a whole. This will address the following requirements:
   - User data with name, email, id, etc.
     - Include a status enum
   - Conversation with a conversation history and multiple users
   - Messages that are comprised of multiple types of data

Conversation data type can have a toggleable Boolean for read receipts, provide one or more users
in the conversation, and keep a history of messages.
Message data types can contain the media type, unique identifiers, timestamp data, as well as a
status enum.

### UI Reliant Methods

Some methods that will be implemented through our data interfaces will not be viable for proper
functional testing until the UI layer begins implementation.

## Decision

We will implement the aforementioned data types to meet the requirements of the user requests.
Later UI implementation will allow us to functionally test properly. The four main data types will
consist of User, Conversation, Message, and Status. The Message data type will contain three
subclasses: Text, Media, and MixMedia.

## Outcomes

We will ensure that the data layer meets all requirements of the user requests and be thoroughly tested
when comes time to implement the UI/Android layer. The data types mentioned will cover the requirements of:
- Storing basic user data as a participant of a conversation
- Conversation history
- Single and group conversations
- Message timestamp
- Read receipts
- Message status
- Sending Mixed Media messages