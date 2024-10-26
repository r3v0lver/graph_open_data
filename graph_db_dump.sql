--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: coloring; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coloring (
    id integer NOT NULL,
    graph_id character varying(32),
    coloring integer[]
);


ALTER TABLE public.coloring OWNER TO postgres;

--
-- Name: coloring_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coloring_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.coloring_id_seq OWNER TO postgres;

--
-- Name: coloring_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.coloring_id_seq OWNED BY public.coloring.id;


--
-- Name: graph; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.graph (
    id character varying(32) NOT NULL,
    vnum integer NOT NULL,
    adjmatrix integer[] NOT NULL,
    simplegraph boolean,
    chromaticnumber integer,
    isbipartite boolean,
    edgecount integer,
    connectedcomponents integer,
    density double precision,
    maxvertexdegree integer
);


ALTER TABLE public.graph OWNER TO postgres;

--
-- Name: coloring id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coloring ALTER COLUMN id SET DEFAULT nextval('public.coloring_id_seq'::regclass);


--
-- Data for Name: coloring; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coloring (id, graph_id, coloring) FROM stdin;
1	graph_1	{0,1,0}
2	graph_1	{1,0,1}
3	graph_2	{0,1,2}
4	graph_2	{1,0,2}
5	graph_3	{0,1,0,1}
6	graph_3	{1,0,1,0}
7	graph_4	{0,1,2,0}
8	graph_4	{1,0,2,1}
9	graph_5	{0,1,0,1}
10	graph_5	{1,0,1,0}
11	graph_6	{0,1,0,1,0}
12	graph_6	{1,0,1,0,1}
13	graph_7	{0,1,2,1,0}
14	graph_7	{1,0,2,0,1}
15	graph_8	{0,1,0,1,0}
16	graph_8	{1,0,1,0,1}
17	graph_9	{0,1,0,1,0}
18	graph_9	{1,0,1,0,1}
19	graph_10	{0,1,2,3,4}
20	graph_10	{4,3,2,1,0}
\.


--
-- Data for Name: graph; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.graph (id, vnum, adjmatrix, simplegraph, chromaticnumber, isbipartite, edgecount, connectedcomponents, density, maxvertexdegree) FROM stdin;
graph_1	3	{{1,1,0},{1,1,1},{0,1,1}}	t	2	t	2	1	0.6667	2
graph_2	3	{{1,1,1},{1,1,1},{1,1,1}}	t	3	f	3	1	1	2
graph_3	4	{{1,1,0,0},{1,1,1,0},{0,1,1,1},{0,0,1,1}}	t	2	t	3	1	0.5	2
graph_4	4	{{1,1,1,0},{1,1,1,1},{1,1,1,1},{0,1,1,1}}	t	3	f	5	1	0.8333	3
graph_5	4	{{1,1,0,1},{1,1,1,0},{0,1,1,1},{1,0,1,1}}	t	2	t	4	1	0.6667	2
graph_6	5	{{1,1,0,0,1},{1,1,1,0,0},{0,1,1,1,0},{0,0,1,1,1},{1,0,0,1,1}}	t	2	t	5	1	0.5	2
graph_7	5	{{1,1,1,0,0},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,1,1},{0,0,1,1,1}}	t	3	f	8	1	0.8	4
graph_8	5	{{1,1,0,1,1},{1,1,1,0,0},{0,1,1,1,1},{1,0,1,1,0},{1,0,1,0,1}}	t	2	t	6	1	0.6	3
graph_9	5	{{1,1,1,0,0},{1,1,0,1,0},{1,0,1,1,1},{0,1,1,1,1},{0,0,1,1,1}}	t	2	t	7	1	0.7	3
graph_10	5	{{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}}	t	5	f	10	1	1	4
\.


--
-- Name: coloring_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coloring_id_seq', 20, true);


--
-- Name: coloring coloring_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coloring
    ADD CONSTRAINT coloring_pkey PRIMARY KEY (id);


--
-- Name: graph graph_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.graph
    ADD CONSTRAINT graph_pkey PRIMARY KEY (id);


--
-- Name: coloring coloring_graph_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coloring
    ADD CONSTRAINT coloring_graph_id_fkey FOREIGN KEY (graph_id) REFERENCES public.graph(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

