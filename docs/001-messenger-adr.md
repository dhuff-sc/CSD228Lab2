# Messenger Project ADR
Date: 2024-02-14

## Status

Proposed, Data Layer underway

## Content

As per client requests, we are faced with a following requirements and problems:
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

### Sending Offline Messages

We can address the issue of being able to send offline messages by prodiving a queue in the form of
an interface with a list of queued messages or a repository.

Repository
Pros:
- More elegant and likely more long-term solution
- Standard practice

Cons:
- Not familiar enough with android/kotlin data repositories
- Tight timeframe
- Need to learn

### UI Reliant Methods

Some methods that will be implemented through our interfaces will not be viable for proper
functional testing until the UI layer begins implementation.

## Decision

Using an interface for offline message queue will be our best bet given the time and resources available.

## Outcomes

We will ensure that the data layer meets all requirements of the user requests and be thoroughly tested
when comes time to implement the UI/Android layer. Some methods will not be able to be properly implemented
such as refreshing the list of messages until the Android layer begins implementation.