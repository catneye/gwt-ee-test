
CREATE TABLE public.nodes
(
    id serial NOT NULL,
    name character varying(256),
    adddate timestamp with time zone,
    idparent integer,
    CONSTRAINT pk_nodes_id PRIMARY KEY (id)
)

CREATE TABLE public.leafs
(
    id serial NOT NULL,
    name character varying(256),
    adddate timestamp with time zone,
    idnodes integer,
    CONSTRAINT pk_leafs_id PRIMARY KEY (id)
)
