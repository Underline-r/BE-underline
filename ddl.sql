create table bookmark
(
    bookmark_id   bigint not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    post_id       bigint,
    user_id       bigint,
    primary key (bookmark_id)
) engine = InnoDB;

create table comment
(
    comment_id    bigint not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    content       varchar(255),
    post_id       bigint,
    user_id       bigint,
    primary key (comment_id)
) engine = InnoDB;

create table hashtag
(
    hashtag_id   bigint not null auto_increment,
    hashtag_name varchar(255),
    post_id      bigint,
    primary key (hashtag_id)
) engine = InnoDB;

create table mail_history
(
    id            bigint not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    log_message   varchar(255),
    recipient     varchar(100),
    success_flag  bit,
    primary key (id)
) engine = InnoDB;

create table notification
(
    notification_id   bigint not null auto_increment,
    notification_type varchar(255),
    search_yn         varchar(255),
    guest_id          bigint,
    host_id           bigint,
    post_id           bigint,
    primary key (notification_id)
) engine = InnoDB;

create table pick
(
    pick_id       bigint not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    post_id       bigint,
    user_id       bigint,
    primary key (pick_id)
) engine = InnoDB;

create table post
(
    post_id       bigint   not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    content       longtext not null,
    reference_id  bigint,
    user_id       bigint   not null,
    primary key (post_id)
) engine = InnoDB;

create table post_category_relation
(
    pcr_id        bigint not null auto_increment,
    category_code varchar(255),
    post_id       bigint,
    primary key (pcr_id)
) engine = InnoDB;

create table reference
(
    reference_id bigint       not null auto_increment,
    title        varchar(255) not null,
    primary key (reference_id)
) engine = InnoDB;

create table refresh_token
(
    user_id       varchar(255) not null,
    refresh_value varchar(255),
    primary key (user_id)
) engine = InnoDB;

create table user_category_relation
(
    ucs_id   bigint not null auto_increment,
    category varchar(255),
    user_id  bigint,
    primary key (ucs_id)
) engine = InnoDB;

create table user_follow_relation
(
    id            bigint not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    from_user_id  bigint,
    to_user_id    bigint,
    primary key (id)
) engine = InnoDB;

create table users
(
    user_id     bigint not null auto_increment,
    authority   varchar(255),
    description varchar(255),
    email       varchar(255),
    image_path  varchar(255),
    nickname    varchar(255),
    password    varchar(255),
    primary key (user_id)
) engine = InnoDB;

alter table post_category_relation
    add constraint CATEGORY_RELATION_UK unique (pcr_id, category_code);

alter table user_category_relation
    add constraint CATEGORY_RELATION_UK unique (user_id, category);

alter table user_follow_relation
    add constraint FOLLOW_RELATION_UK unique (to_user_id, from_user_id);

alter table bookmark
    add constraint BOOKMARK_POST_FK foreign key (post_id) references post (post_id);

alter table bookmark
    add constraint BOOKMARK_USERS_FK foreign key (user_id) references users (user_id);

alter table comment
    add constraint COMMENT_POST_FK foreign key (post_id) references post (post_id);

alter table comment
    add constraint COMMENT_USERS_FK foreign key (user_id) references users (user_id);

alter table hashtag
    add constraint HASHTAG_POST_FK foreign key (post_id) references post (post_id);

alter table notification
    add constraint NOTIFICATION_USERS_FK1 foreign key (guest_id) references users (user_id);

alter table notification
    add constraint NOTIFICATION_USERS_FK2 foreign key (host_id) references users (user_id);

alter table notification
    add constraint NOTIFICATION_POST_FK foreign key (post_id) references post (post_id);

alter table pick
    add constraint PICK_POST_FK foreign key (post_id) references post (post_id);

alter table pick
    add constraint PICK_USERS_FK foreign key (user_id) references users (user_id);

alter table post
    add constraint POST_REFER_FK foreign key (reference_id) references reference (reference_id);

alter table post
    add constraint POST_USERS_FK foreign key (user_id) references users (user_id);

alter table post_category_relation
    add constraint PCR_POST_FK foreign key (post_id) references post (post_id);

alter table user_category_relation
    add constraint UCR_USERS_FK foreign key (user_id) references users (user_id);

alter table user_follow_relation
    add constraint UFR_FROM_USER_FK foreign key (from_user_id) references users (user_id);

alter table user_follow_relation
    add constraint UFR_TO_USER_FK foreign key (to_user_id) references users (user_id);