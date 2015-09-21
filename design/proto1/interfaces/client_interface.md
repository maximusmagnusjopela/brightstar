# Client facing interfaces
The client interfaces should allow the client to register to the queue effortlessly. For prototype 1, registration 
will be done through a web form. 

## Web form registration interface

### Generalities
the web form should allow the user to:

1. Enter is name.
2. Enter is mobile phone number.

Once the client has entered is information, he should receive an sms message that prompt him to enter a confirmation
code in the web form. This is to make sure that when the client registers to the queue, he has the registered mobile
phone in hand. 

The confirmation code page should include a resend button.

If the client does not enter the confirmation code before a configurable amount of time, the client page must return
to the index page (the font page)

### Internationalization
It should be easy to support multiple languages through the application

### Access to web form registration interface
The web form will be publicly acessible. Nevertheless, the web form interface must implement IP access control
for testing purposes.

### CAPTCHA
The web form should include a CAPTCHA challenge to make authomatic registration impossible (difficult)

### Colors
Colors theme should be easily customizable. For prototype, Used colors will be pastel orange, pastel yellow,black
and white..

## Client interface interaction use case
Interaction with client interface. Also describes what happens in the backend

### Registration, Status, Call, Close
In this use case, the client registers via the webform, later asks for a status. Later, the client gets
called by a consumer and finally, the consumer closes the case and the client leaves the queue.

1. Client connects to www.business.com/register
2. Client receive a prompt to enter is name, is mobile phone number, and the response to a captcha challenge.
3. Client submits the form and server returns the token confirmation page.
4. Client submits the confirmation token.
5. Server returns a registration confirmation message.
6. Client receives an SMS confirmation message after queue entry. Message contains expected wait time if enough
statistics exist. If not enough stats, returns the position of the client in the queue. Message contains
instruction to interact with the system (txt: status to get update and leave to leave the queue)
7. Clients sends an sms to receive status
8. Server enqueues a status request for the given mobile phone number.
9. Message is routed to the status queue.
10. Status service computes status information and pushes sms to sms send service.
11. Client receives is sms update.
12. Consumer calls the client.
13. Client receives the SMS call message with instruction on where to go.
14. Consumer process the client and mark him as closed.
15. Consumer leaves the queue.



