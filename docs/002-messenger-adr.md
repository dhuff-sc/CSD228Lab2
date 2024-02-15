# Messenger Project ADR - Offline Messages
Date: 2024-02-14

## Status

Data Layer underway

## Content

Project requires messenger app to queue offline messages and send them once online.
This will require the queue to be implemented regardless of which conversation,
be local to the conversation, and be able to send messages to a single user or a group of users.

## Solutions

### Sending Offline Messages

We can address the issue of being able to send offline messages by providing a queue in the form of
an interface with a list of queued messages or a repository.

Repository
Pros:
- More elegant and likely more long-term solution
- Standard practice

Cons:
- Not familiar enough with android/kotlin data repositories
- Tight timeframe
- Need to learn

## Decision

Using an interface for offline message queue will be our best bet given the time and resources available.
We will implement the interface to store messages in a queue and send them when the user is online.

## Outcomes

All messaged sent in a conversation will be run through the queue to check if the application
is connected to a wireless internet connection. This will ensure that no messages are potentially
lost due to a cut internet connection. This will meet the user requirement of sending messages
when offline.