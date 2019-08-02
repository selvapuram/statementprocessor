insert into offer (id, jobtitle, createdtime) values(1, 'SeniorJavaEngineer', now());
insert into offer (id, jobtitle, createdtime) values(2, 'SeniorScalaEngineer', now());
insert into offer (id, jobtitle, createdtime) values(3, 'SeniorPythonEngineer', now());

insert into application (id, offer_id, applicationstatus, candidateemail, resumetext, createdtime, updatedtime) values(1, 2, 'APPLIED', 'testuser1@testmail.com', '5+ Years of experience with Spring', now(), now());
insert into application (id, offer_id, applicationstatus, candidateemail, resumetext, createdtime, updatedtime) values(2, 2, 'INVITED', 'testuser2@testmail.com', '6+ Years of experience with Spring', now(), now());

insert into customer(id, username, password, token) values(1, 'USER', 'USER', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiVVNFUiIsInBhc3N3b3JkIjoiVVNFUiIsInJvbGUiOiJST0xFX0dVRVNUIn0.V0a7QuDBgsM7_CaA8q8Z1Jsc54WsJHZ20SnVhIvBsmc');
insert into customer(id, username, password, token) values(2, 'ADMIN', 'ADMIN', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiQURNSU4iLCJwYXNzd29yZCI6IkFETUlOIiwicm9sZSI6IlJPTEVfQURNSU4ifQ.pwYxrnUJiaEoYfQqC4uKHa3LpZnHna20Ot92zzJEvKw');