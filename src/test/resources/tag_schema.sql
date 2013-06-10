drop table if exists tag_scan;
drop table if exists tag;

create table tag (
  seq_id bigint not null auto_increment,
  tag_id bigint not null,
  advertiser_account_id bigint not null,
  tag_type enum('market_direct','lift','rtb','enterprise') not null,
  ad_source text not null,
  currency varchar(10),  
  created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  primary key(seq_id),
  unique key (tag_id, advertiser_account_id, tag_type)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



create table tag_scan (
    id bigint not null auto_increment,
    tag_seq_id bigint not null,
    priority int not null,
    random float default 0,
    primary key (id),
    foreign key (tag_seq_id) references tag (seq_id),
    index (priority,  random)
) ENGINE=INNODB DEFAULT CHARSET=utf8;