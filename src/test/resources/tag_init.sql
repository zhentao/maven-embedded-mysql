insert into tag (seq_id, tag_id, advertiser_account_id, tag_type,  ad_source) values (1, 1, 1, 'lift', '<body><div id=''1''></div></body>');
insert into tag (seq_id, tag_id, advertiser_account_id, tag_type,  ad_source) values (2, 2, 2, 'lift', '<body><div id=''1''></div></body>');

insert into tag_scan (id, tag_seq_id, priority, random) values (1, 1, 10, rand());
insert into tag_scan (id, tag_seq_id, priority, random) values (2, 1, 20, rand());
insert into tag_scan (id, tag_seq_id, priority, random) values (3, 2, 15, rand());
insert into tag_scan (id, tag_seq_id, priority, random) values (4, 2, 30, rand());
insert into tag_scan (id, tag_seq_id, priority, random) values (5, 1, 60, rand());
insert into tag_scan (id, tag_seq_id, priority, random) values (6, 2, 50, rand());