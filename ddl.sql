-- 배치 관련 테이블

create table BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID bigint       not null
        primary key,
    VERSION         bigint       null,
    JOB_NAME        varchar(100) not null,
    JOB_KEY         varchar(32)  not null,
    constraint JOB_INST_UN
        unique (JOB_NAME, JOB_KEY)
)engine = InnoDB;

create table BATCH_JOB_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
)engine = InnoDB;

create table BATCH_JOB_EXECUTION_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
)engine = InnoDB;

create table BATCH_STEP_EXECUTION_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
)engine = InnoDB;

create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID           bigint        not null
        primary key,
    VERSION                    bigint        null,
    JOB_INSTANCE_ID            bigint        not null,
    CREATE_TIME                datetime(6)   not null,
    START_TIME                 datetime(6)   null,
    END_TIME                   datetime(6)   null,
    STATUS                     varchar(10)   null,
    EXIT_CODE                  varchar(2500) null,
    EXIT_MESSAGE               varchar(2500) null,
    LAST_UPDATED               datetime(6)   null,
    JOB_CONFIGURATION_LOCATION varchar(2500) null,
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
) engine = InnoDB;

create table BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    VERSION            bigint        not null,
    STEP_NAME          varchar(100)  not null,
    JOB_EXECUTION_ID   bigint        not null,
    START_TIME         datetime(6)   not null,
    END_TIME           datetime(6)   null,
    STATUS             varchar(10)   null,
    COMMIT_COUNT       bigint        null,
    READ_COUNT         bigint        null,
    FILTER_COUNT       bigint        null,
    WRITE_COUNT        bigint        null,
    READ_SKIP_COUNT    bigint        null,
    WRITE_SKIP_COUNT   bigint        null,
    PROCESS_SKIP_COUNT bigint        null,
    ROLLBACK_COUNT     bigint        null,
    EXIT_CODE          varchar(2500) null,
    EXIT_MESSAGE       varchar(2500) null,
    LAST_UPDATED       datetime(6)   null,
    constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
)engine = InnoDB;

create table BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
)engine = InnoDB;

create table BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID bigint       not null,
    TYPE_CD          varchar(6)   not null,
    KEY_NAME         varchar(100) not null,
    STRING_VAL       varchar(250) null,
    DATE_VAL         datetime(6)  null,
    LONG_VAL         bigint       null,
    DOUBLE_VAL       double       null,
    IDENTIFYING      char         not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
)engine = InnoDB;


create table BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
)engine = InnoDB;

create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID           bigint        not null
        primary key,
    VERSION                    bigint        null,
    JOB_INSTANCE_ID            bigint        not null,
    CREATE_TIME                datetime(6)   not null,
    START_TIME                 datetime(6)   null,
    END_TIME                   datetime(6)   null,
    STATUS                     varchar(10)   null,
    EXIT_CODE                  varchar(2500) null,
    EXIT_MESSAGE               varchar(2500) null,
    LAST_UPDATED               datetime(6)   null,
    JOB_CONFIGURATION_LOCATION varchar(2500) null,
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
)engine = InnoDB;

-- 배치 테이블 끝

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
    created_date  datetime(6),
    modified_date datetime(6),
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
    source_id  bigint,
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

create table source
(
    source_id bigint       not null auto_increment,
    created_date  datetime(6),
    modified_date datetime(6),
    title     varchar(255) not null,
    primary key (source_id)
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
    created_date  datetime(6),
    modified_date datetime(6)
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
    add constraint POST_SOURCE_FK foreign key (source_id) references source (source_id);

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