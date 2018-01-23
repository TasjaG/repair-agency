INSERT INTO users (user_login, user_password, user_f_name, user_l_name, user_email) values ("admin", "admin", "Admin", "Admin", "admin2018@gmail.com");
INSERT INTO users (user_login, user_password, user_f_name, user_l_name, user_email) values ("manager", "manager", "Manager", "Manager", "manager2018@gmail.com");
INSERT INTO users (user_login, user_password, user_f_name, user_l_name, user_email) values ("repairman", "repairman", "Repairman", "Repairman", "repairman2018@gmail.com");
INSERT INTO users (user_login, user_password, user_f_name, user_l_name, user_email) values ("user1", "user1", "User1", "User1", "user1_2018@gmail.com"); 

INSERT INTO user_types (role, description) values ("admin", "site's administrator");
INSERT INTO user_types (role, description) values ("manager", "repair agency's manager");
INSERT INTO user_types (role, description) values ("repairman", "repair agency's repairman");
INSERT INTO user_types (role, description) values ("user", "site's user");

INSERT INTO users_and_types (utype_id, user_id) values (1, 1);
INSERT INTO users_and_types (utype_id, user_id) values (2, 2);
INSERT INTO users_and_types (utype_id, user_id) values (3, 3);
INSERT INTO users_and_types (utype_id, user_id) values (4, 4);

INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #1", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #2", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #3", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #4", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #5", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #6", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #7", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #8", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #9", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #10", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #11", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #12", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #13", CURRENT_TIMESTAMP(), null, 4);
INSERT INTO comments (comment_text, date_created, date_edited, user_id) values ("Comment #14", CURRENT_TIMESTAMP(), null, 4);

INSERT INTO applications (product_name, product_comment, date_added, application_status,
	application_comment, date_processed, user_id) values ("ProductName1",null,CURRENT_TIMESTAMP(),
    "accepted",null,current_timestamp(),2);
INSERT INTO applications (product_name, product_comment, date_added, application_status,
	application_comment, date_processed, user_id) values ("ProductName2",null,CURRENT_TIMESTAMP(),
    "accepted",null,current_timestamp(),3);
INSERT INTO applications (product_name, product_comment, date_added, application_status,
	application_comment, date_processed, user_id) values ("ProductName3",null,CURRENT_TIMESTAMP(),
    "waiting",null,null,4);
INSERT INTO applications (product_name, product_comment, date_added, application_status,
	application_comment, date_processed, user_id) values ("ProductName4",null,CURRENT_TIMESTAMP(),
    "waiting",null,null,4);
INSERT INTO applications (product_name, product_comment, date_added, application_status,
	application_comment, date_processed, user_id) values ("ProductName5",null,CURRENT_TIMESTAMP(),
    "waiting",null,null,4);

INSERT INTO accepted_applications (aa_product_name, aa_product_comment, aa_price, aa_status, 
		date_completed, application_id, user_id) values ("ProductName1",null,100.0,"waiting",null,1,2);
INSERT INTO accepted_applications (aa_product_name, aa_product_comment, aa_price, aa_status, 
		date_completed, application_id, user_id) values ("ProductName2",null,150.0,"waiting",null,2,3);