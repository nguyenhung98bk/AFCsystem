--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

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
-- Name: gate; Type: TABLE; Schema: public; Owner: postgres
--


CREATE TABLE public.onewayticket (
    "owid" character varying NOT NULL,
    "owbalance" real NOT NULL,
    "owstatus" boolean NOT NULL
);


ALTER TABLE public.onewayticket OWNER TO postgres;

--


CREATE TABLE public.prepaidcard (
    "cardid" character varying NOT NULL,
    "cardpseudocode" character varying NOT NULL,
    "cardcorrescode" character varying NOT NULL,
    "cardbalance" real NOT NULL,
    "cardstatus" int NOT NULL
);


ALTER TABLE public.prepaidcard OWNER TO postgres;

--
-- Name: station; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.station (
    "stationid" char NOT NULL,
    "stationname" character varying NOT NULL,
    "distance" float NOT NULL
);


ALTER TABLE public.station OWNER TO postgres;

--
-- Name: twentyfourhoursticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.twentyfourhoursticket (
    "tfid" character varying,
    "tfcode" character varying NOT NULL,
    "tfbalance" real NOT NULL,
    "tfstatus" boolean NOT NULL,
    "tfstart" time with time zone NOT NULL,
    "tfend" time with time zone NOT NULL
);


ALTER TABLE public.twentyfourhoursticket OWNER TO postgres;

--
-- Name: gate gate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

CREATE TABLE public.tripinfo (
	"id" SERIAL,
	"idstartstation" char NOT NULL,
	"idendstation" char,
	"pseudocode" character varying
);

ALTER TABLE public.tripinfo OWNER TO postgres;


--
-- Name: onewayticket onewayticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.onewayticket
    ADD CONSTRAINT onewayticket_pkey PRIMARY KEY ("owid");




--
-- Name: prepaidcard prepaidcard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prepaidcard
    ADD CONSTRAINT prepaidcard_pkey PRIMARY KEY ("cardid");


--
-- Name: station station_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.station
    ADD CONSTRAINT station_pkey PRIMARY KEY ("stationid");


--
-- Name: twentyfourhoursticket twentyfourhoursticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.twentyfourhoursticket
    ADD CONSTRAINT twentyfourhoursticket_pkey PRIMARY KEY ("tfid");


--
-- Name: twentyfourhoursticket belongpass; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tripinfo
	ADD CONSTRAINT tripinfo_pkey PRIMARY KEY ("id");



ALTER TABLE ONLY public.tripinfo
    ADD CONSTRAINT "startstation" FOREIGN KEY ("idstartstation") REFERENCES public.station("stationid");

ALTER TABLE ONLY public.tripinfo
    ADD CONSTRAINT "endstation" FOREIGN KEY ("idendstation") REFERENCES public.station("stationid");

--
-- PostgreSQL database dump complete
--

