create table contact (id bigint not null, primary key (id)) engine=InnoDB;
create table diagnose (id bigint not null, description varchar(255), tag varchar(255), primary key (id)) engine=InnoDB;
create table disease (id bigint not null, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table medical_record (id bigint not null, date varchar(255), text varchar(255), diagnose_id bigint, primary key (id)) engine=InnoDB;
create table patient (id varchar(255) not null, blood_group varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255), primary key (id)) engine=InnoDB;
create table patient_contacts (patient_id varchar(255) not null, contacts_id bigint not null) engine=InnoDB;
create table patient_diseases (patient_id varchar(255) not null, diseases_id bigint not null) engine=InnoDB;
alter table patient_diseases add constraint UK_sfagwge4np4j7ph95bvb5gqbk unique (diseases_id);
alter table medical_record add constraint FKdyhnq9ietqdbom2kpn8r1lr4 foreign key (diagnose_id) references diagnose (id);
alter table patient_contacts add constraint FK987c6tue0bs67yh1xo3gbev4l foreign key (contacts_id) references contact (id);
alter table patient_contacts add constraint FK62hfbm0jp9wxco1ml37l2snl foreign key (patient_id) references patient (id);
alter table patient_diseases add constraint FK4hd8rm289m7o2i7qmobrtu3uv foreign key (diseases_id) references disease (id);
alter table patient_diseases add constraint FK1q2kwe3nmnj8b0ccl71p2g30d foreign key (patient_id) references patient (id);
