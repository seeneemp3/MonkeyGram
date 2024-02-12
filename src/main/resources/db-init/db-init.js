db = db.getSiblingDB('monkeygram');

db.users.insertMany([
  {
    "_id": ObjectId("65c9c3aad856f008d72e6713"),
    "username": "user2",
    "nickname": "user2",
    "password": "$2a$10$VxKblhEmZ/5WePXU5RJXd.XmJWfDF7FhozDLxYdPXRr/W5hicIbmS",
    "roles": ["ROLE_USER"],
    "_class": "com.personal.monkeyGram.model.User"
  }
]);
