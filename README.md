**Decisions I have made**

- A microservice called 'pbx' to control de integrations of external PBX into the system.
The intention with this microservice is to act as some kind of configuration microservice,
  where the other microservice can read the configuration they to connect to the proper PBX.
  For that, the fields url and endpoints in the database are the main ones. Although it's not
  developed, my intention was to create some kind of structure to the endpoints field: a JSON
  containing some main operations as keys.
- A microservice to control the CDRs (call_detailed_record) and a microservice to control the phone book (phone_book).
- Each microservice contains a test for each endpoint.
- Each microservice has a table associated. As they are separated, they are pretty simple tables.
- The phone-book service has a method called getContactByNumber(). As you specify on the requirements,
  it's necessary to match phone numbers with names, so this method could be used to search for a contact
  given its phone number.
- Gateway: This is the API Gateway where it's possible to control the access
to the different microservice through a common API. Although it's not implemented, it's possible
  to restrict some endpoints in case it's necessary.
