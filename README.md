# Docs

## Database Schema

https://docs.google.com/document/d/18axX7pOYaKnhBWIyrSuXaggTtqyFLPWoFtzR41PbCu4/edit

# Pinned

Authentication: by HTTP Header

format: KEY: "Authorization", VALUE: "Bearer " + {token}

base url: /api/v1

# Task

## Walkthrough

Walkthrough1: user registers -> account info saved in the database -> user login -> server generates a token and returns it -> client receives the token and saves it

Walkthrough2: user fills the product info form -> client sends a product creating request with the token -> product is saved to the database -> user tries to get info of the newly-created product

**Login and get the token**

The registration step has been omitted

<img width="1077" alt="Screen Shot 2023-02-10 at 6 34 56 PM" src="https://user-images.githubusercontent.com/48000537/218227186-91869a66-944b-40e2-98aa-4dca5aafaed7.png">

**Add a new product to the server**

Another prodoct adding request has been omitted

Don't forget the header!

<img width="1097" alt="Screen Shot 2023-02-10 at 6 35 03 PM" src="https://user-images.githubusercontent.com/48000537/218227188-718ac760-9607-48f2-b64c-02d5e04e3790.png">

**Query all the products**

<img width="1072" alt="Screen Shot 2023-02-10 at 6 34 49 PM" src="https://user-images.githubusercontent.com/48000537/218227185-14239073-3657-4e43-82e9-7a78abe7bc71.png">

## Subtask

- Database schema design
- Interface Documentation
  - Endpoint design (in web controller)
  - REST API
- Authentication and access-control implementation
  - Spring Security
  - JSON Web Token
- Test Data
  - Write a script to generate
- Cloud database
  - For test
  - For production

# More Tasks

- Sample data generator

  Used for testing and demonstration purposes in our web-based system.

  1. Showcase the system's functionality: Sample data helps to demonstrate the features and capabilities of a web-based system to potential customers or users.
  2. Testing and Debugging: Sample data can be used to test the system's functionality, performance and stability, and to identify and resolve any bugs or issues.
  3. Training: Sample data can be used to train users or to provide hands-on training to help them understand how to use the system effectively.
  4. Design and Development: Sample data can be used to support the design and development process of a web-based system, helping to ensure that it meets the requirements and expectations of users.

- GroupMe Integration:

  1. API Integration: To integrate the application with GroupMe, we need to use GroupMe's API, which allows it to interact with GroupMe's chat functionality and to send messages to the group chat from the application.
  2. Create a GroupMe Bot: To send messages from the application to the GroupMe chat, we need to create a GroupMe Bot. A Bot is a special type of user account that can interact with GroupMe chats automatically.
  3. Authorize the Bot: To allow the application to interact with GroupMe, we need to obtain authorization from GroupMe. We can do this by creating a GroupMe account and following the steps provided by GroupMe to set up a Bot (may fail).
  4. Program the integration: Once we have created a GroupMe Bot and obtained authorization, we can start programming the integration between the application and GroupMe. 
  5. Test and Deploy the integration: After programming the integration, test it thoroughly to make sure that it works as expected. We can use sample data to test the functionality, after which we should deploy it and make it available to users. Our users will then be able to use the application and the GroupMe chat together to trade second-handed goods within the school.

- Chat server

  If we do the GroupMe Integration, we can probably save our time and efforts from developing our on chat server. Users can chat on GroupMe!

- Combination of sigma drafts

  Make the whole design cohesive and the style harmonious.

# Process

1. Determine the database schema design
2. Capture requirements from front-end developers
3. Design endpoints (RESTful)
4. Implement authentication with JWT (ref: https://github.com/thiagoprocaci/springboot-react-jwt)
5. Create an interface documentation including API and JWT usage
6. Implement application logic
7. Write a script to generate test data
8. Integrate with the front-end system
9. Deploy and test

# Work done

- Skeleton
- Framework
- Figma

