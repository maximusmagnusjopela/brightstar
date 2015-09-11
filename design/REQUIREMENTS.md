# brightstar system requirements
Brightstar is an SMS based queueing system. The requirements for the full system will be laid out incrementally
as prototypes are constructed. Each prototype will add a level of complexity to the system until full system
funtionality is achieved

## Prototype I
For the first prototype, the system is required to (client side requirement):

1. Allow a client to register to the queue with a web browser client.
2. Notify the client of it's entry in the queue by sms.
    a) The sms message must include the current position of the client in the queue.
    b) The sms message must include the expected waiting time in the queue.
3. Notify the client when is remaining expected time to wait if less then a certain configurable amount of time.
4. Notify the client when is turn to be service has come.
    a) the sms message must properly route the client to where he will be serviced.

The system is also required to (service side requirement, all communication from clerk to system happen via web
browser):

1. Allow a service clerk to call on client via interface.
2. Allow a service clerk to notify the system that a customer did not come when called.
3. Allow a service clerk to notify the system that a customer as been serviced.



