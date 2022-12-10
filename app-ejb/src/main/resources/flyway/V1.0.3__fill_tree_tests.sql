with rows as (insert into nodes (name, adddate, idparent) values ('node-001', now(), null) returning id),
    rows1 as (insert into nodes (name, adddate, idparent) values ('node-002', now(), (select id from rows)) returning id)
insert into leafs (name, adddate, idnodes) values 
('leaf-001001', now(),(select id from rows)),
('leaf-001002', now(),(select id from rows)),
('leaf-001002', now(),(select id from rows)),
('leaf-002001', now(), (select id from rows1)),
('leaf-002002', now(), (select id from rows1)),
('leaf-002002', now(), (select id from rows1));


with rows as (insert into nodes (name, adddate, idparent) values ('node-003', now(), null) returning id),
    rows1 as (insert into nodes (name, adddate, idparent) values ('node-004', now(), (select id from rows)) returning id),
    rows2 as (insert into nodes (name, adddate, idparent) values ('node-005', now(), (select id from rows)) returning id)
insert into leafs (name, adddate, idnodes) values 
('leaf-003001', now(),(select id from rows)),
('leaf-003002', now(),(select id from rows)),
('leaf-003002', now(),(select id from rows)),
('leaf-004001', now(),(select id from rows1)),
('leaf-004002', now(),(select id from rows1)),
('leaf-004002', now(),(select id from rows1)),
('leaf-005001', now(),(select id from rows2)),
('leaf-005002', now(),(select id from rows2)),
('leaf-005002', now(),(select id from rows2));
