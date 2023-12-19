Social Media Platform - README

Overview
This Social Media Platform is a web application built using Java and the Spring Boot framework. The platform provides various features including user management, post creation, commenting, liking, sharing, messaging, friend requests, and following functionalities. The application also incorporates Spring Security for user authentication and authorization.

Technologies Used
Java: The primary programming language used for backend development.
Spring Boot: A powerful framework for building Java-based enterprise applications.
Spring Security: Provides authentication, authorization, and protection against common security vulnerabilities.
Spring Data JPA: Simplifies database access using the Java Persistence API.
Mongodb: The database management system used for data storage.
RESTful API: The application follows REST architectural principles for web service development.
Maven: A build automation tool for managing dependencies and building the project.
Git: Version control system for tracking changes in the source code.
GitHub: The project is hosted on GitHub for version control and collaboration.


Functionality

User Management

Register User: Endpoint /Socialmedia/user/register allows users to register on the platform.
Login User: Endpoint /Socialmedia/user/login facilitates user login with proper authentication.

User Profile

Create User Profile: Endpoint /create in UserProfileController allows users to create a profile associated with their account.

User Interaction

Like, Comment, Share Posts: Endpoints in UserInteractionController enable users to like, comment, and share posts.
Get User Likes, Comments, Shares: Endpoints /likes/{userId}, /comments/{userId}, /shares/{userId} retrieve user-specific interactions.

Post Management

Create Post: Endpoint /create/post in PostController allows users to create posts with optional image attachments.
Add Comment: Endpoint /{postId} in PostController lets users add comments to specific posts.
Get Posts for User: Endpoint /api/posts/user/{ownerId} retrieves posts for a given user, with an optional viewer ID for personalized content.

Messaging

Send Message: Endpoint /send in MessageController allows users to send messages to each other.
Get Messages Between Users: Endpoint /user/{senderId}/conversation/{receiverId} retrieves messages between two users.

Friendships

Send Friend Request: Endpoint /request in FriendshipController enables users to send friend requests.
Accept/Reject Friend Request: Endpoints /accept and /reject in FriendshipController handle friend request responses.

Follows

Follow/Unfollow User: Endpoints /follow and /unfollow in FollowController allow users to follow and unfollow each other.


Security
The application utilizes Spring Security for user authentication and authorization. User roles such as USER and ADMIN are defined to control access to various functionalities. Secure password storage practices are employed to protect user credentials.

Database Configuration
The application uses Mongodb as the database. Database configuration details can be found in the application.properties file.

properties

spring.data.mongodb.host=localhost

spring.data.mongodb.port=27017

spring.data.mongodb.database=socialmedia

Note

Ensure that you have Java, Maven, and Mongodb installed on your machine before running the application.

Feel free to explore the codebase and enhance the application based on your requirements! 

Happy coding!

Thanks!
