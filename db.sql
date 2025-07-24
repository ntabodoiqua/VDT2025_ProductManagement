--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-07-24 16:05:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 217 (class 1259 OID 16448)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id character varying(255) NOT NULL,
    description character varying(255),
    image character varying(255),
    name character varying(255) NOT NULL,
    created_by character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    image_name character varying(255),
    updated_at timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16505)
-- Name: invalidated_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invalidated_token (
    id character varying(255) NOT NULL,
    expiry_date timestamp(6) without time zone
);


ALTER TABLE public.invalidated_token OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16524)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id character varying(255) NOT NULL,
    active boolean NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    description character varying(255),
    image_name character varying(255),
    name character varying(255) NOT NULL,
    price numeric(19,2) NOT NULL,
    quantity integer NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    category_id character varying(255) NOT NULL,
    created_by character varying(255) NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (0)::numeric)),
    CONSTRAINT product_quantity_check CHECK ((quantity >= 0))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16464)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    name character varying(255) NOT NULL,
    description character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16510)
-- Name: uploaded_file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.uploaded_file (
    id character varying(255) NOT NULL,
    file_name character varying(255) NOT NULL,
    file_size bigint,
    file_type character varying(255),
    original_file_name character varying(255),
    uploaded_at timestamp(6) without time zone,
    user_id character varying(255) NOT NULL
);


ALTER TABLE public.uploaded_file OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16471)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id character varying(255) NOT NULL,
    avatar_name character varying(255),
    created_at timestamp(6) without time zone NOT NULL,
    dob date,
    email character varying(255) NOT NULL,
    enabled boolean NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    username character varying(255) NOT NULL,
    role_id character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 4933 (class 0 OID 16448)
-- Dependencies: 217
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, description, image, name, created_by, created_at, image_name, updated_at) FROM stdin;
7704efac-90af-4791-9b1e-70815efe6c81	Đồ gia dụng là những hàng hóa và các sản phẩm được sản xuất, chế tạo, mua bán với mục đích chủ yếu là sử dụng trong các hộ gia đình	\N	Đồ gia dụng	74d03f51-1261-4abb-a526-ce3a909d3019	2025-07-24 14:43:33.494441	\N	2025-07-24 14:43:33.494441
bd088d3f-3f46-4735-b0eb-31bda75aa0e6	Danh mục mặc định cho các sản phẩm chưa được phân loại	\N	Chưa phân loại	fa820754-921c-4208-a60c-07bc8206e07e	2025-07-24 14:17:20.04266	e52f7b39-3092-49b2-b4ae-ca9bb6906a3c_uncategorized.png	2025-07-24 14:51:01.124257
\.


--
-- TOC entry 4936 (class 0 OID 16505)
-- Dependencies: 220
-- Data for Name: invalidated_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invalidated_token (id, expiry_date) FROM stdin;
3008724b-3161-4d28-a3c4-16ea38f7b679	2025-07-23 16:20:46
\.


--
-- TOC entry 4938 (class 0 OID 16524)
-- Dependencies: 222
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, active, created_at, description, image_name, name, price, quantity, updated_at, category_id, created_by) FROM stdin;
3cab88b6-a82e-443a-a313-6f7a1316b021	t	2025-07-24 15:44:57.866696	“Chạm” thiên nhiên trong từng khoảnh khắc sống cùng bộ nồi chảo Elmich Nature.	8c327101-561c-4626-ba7a-20fde4dde85a_chao_chong_dinh.webp	Chảo chống dính vân gỗ Elmich Nature EL-5947MG	12000000.00	100	2025-07-24 15:56:08.838397	7704efac-90af-4791-9b1e-70815efe6c81	fa820754-921c-4208-a60c-07bc8206e07e
\.


--
-- TOC entry 4934 (class 0 OID 16464)
-- Dependencies: 218
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (name, description) FROM stdin;
ADMIN	Administrator role with full access
GUEST	Guest role to be assigned to new users. Can view products and categories.
MANAGER	Manager role with access to products and categories
\.


--
-- TOC entry 4937 (class 0 OID 16510)
-- Dependencies: 221
-- Data for Name: uploaded_file; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.uploaded_file (id, file_name, file_size, file_type, original_file_name, uploaded_at, user_id) FROM stdin;
b6fc4214-ccfa-460e-beef-4760044d81c3	22fc2b5b-0140-4083-bf96-533402a20f42_Nitro_Wallpaper_04_3840x2400.jpg	3154850	image/jpeg	Nitro_Wallpaper_04_3840x2400.jpg	2025-07-23 16:26:21.611133	74d03f51-1261-4abb-a526-ce3a909d3019
14c33b7a-d572-4d7c-ac27-461b375d8983	e52f7b39-3092-49b2-b4ae-ca9bb6906a3c_uncategorized.png	1644	image/png	uncategorized.png	2025-07-24 14:51:01.015245	fa820754-921c-4208-a60c-07bc8206e07e
010a14a3-d39f-4d1a-9503-262d4bc5089a	8c327101-561c-4626-ba7a-20fde4dde85a_chao_chong_dinh.webp	107496	image/webp	chao_chong_dinh.webp	2025-07-24 15:47:45.521028	fa820754-921c-4208-a60c-07bc8206e07e
\.


--
-- TOC entry 4935 (class 0 OID 16471)
-- Dependencies: 219
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, avatar_name, created_at, dob, email, enabled, first_name, last_name, password, phone, updated_at, username, role_id) FROM stdin;
fa820754-921c-4208-a60c-07bc8206e07e	\N	2025-07-23 10:52:16.060637	\N	admin@gmail.com	t	\N	\N	$2a$10$Yke00hLzBxIV0DpxZ78DBenzGroWbnqleVLI3MUKWT5j5mBIdBYtC	0123456789	2025-07-23 10:52:16.060637	admin	ADMIN
2a31d054-6bd7-4f15-8097-703640799c3b	\N	2025-07-23 15:13:33.087363	1999-08-25	janedoe@example.com	t	Jane	Doe	$2a$10$2hDD.e8VUcGh0GQtM9GonuLuaSBlnUwQ4Jzy4x5BvOlhQpNVvuqL6	0912345678	2025-07-23 15:13:33.088341	janedoe456	GUEST
e94bf2e9-0962-43c5-8d57-d447dd4a91fd	\N	2025-07-23 15:16:16.505766	1995-12-01	nguyenvana@example.com	t	Văn	Nguyễn	$2a$10$eVtYdEB2dX71.0v.z7c9tONhjegFUTlmbwOTcByrSZn3RZ1sn5I1q	0988123456	2025-07-23 15:16:16.505766	nguyenvana	GUEST
d15c2b34-e584-4125-8903-6165ca0e3509	\N	2025-07-23 15:18:42.834311	1998-04-22	thu.tran@example.com	t	Ngọc Thư	Trần	$2a$10$CESareyZO2fiKDwlrjr48uxceln9vzuK0PorPAZSAK3GDbny.HDki	0911223344	2025-07-23 15:18:42.834311	tranngocthu	GUEST
74d03f51-1261-4abb-a526-ce3a909d3019	22fc2b5b-0140-4083-bf96-533402a20f42_Nitro_Wallpaper_04_3840x2400.jpg	2025-07-23 14:47:35.228254	2000-05-15	anhnta2004@gmail.com	t	John	Doe	$2a$10$6XZLUIeOqwODdGwtIDr5iumOC9DrzaYZe22HeHZojxjXwDHOxLhRK	0987654321	2025-07-24 14:35:05.934861	johndoe123	MANAGER
\.


--
-- TOC entry 4764 (class 2606 OID 16454)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 4776 (class 2606 OID 16509)
-- Name: invalidated_token invalidated_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invalidated_token
    ADD CONSTRAINT invalidated_token_pkey PRIMARY KEY (id);


--
-- TOC entry 4782 (class 2606 OID 16532)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 4766 (class 2606 OID 16470)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (name);


--
-- TOC entry 4778 (class 2606 OID 16518)
-- Name: uploaded_file uk3gaoms3rxeq6gfff1dvbqmqa3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uploaded_file
    ADD CONSTRAINT uk3gaoms3rxeq6gfff1dvbqmqa3 UNIQUE (file_name);


--
-- TOC entry 4768 (class 2606 OID 16479)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 4770 (class 2606 OID 16481)
-- Name: users ukdu5v5sr43g5bfnji4vb8hg5s3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukdu5v5sr43g5bfnji4vb8hg5s3 UNIQUE (phone);


--
-- TOC entry 4772 (class 2606 OID 16483)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 4780 (class 2606 OID 16516)
-- Name: uploaded_file uploaded_file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uploaded_file
    ADD CONSTRAINT uploaded_file_pkey PRIMARY KEY (id);


--
-- TOC entry 4774 (class 2606 OID 16477)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4786 (class 2606 OID 16533)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 4784 (class 2606 OID 16489)
-- Name: users fk4qu1gr772nnf6ve5af002rwya; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk4qu1gr772nnf6ve5af002rwya FOREIGN KEY (role_id) REFERENCES public.role(name);


--
-- TOC entry 4785 (class 2606 OID 16519)
-- Name: uploaded_file fkjfokvdq226ae1srxl2eaw3h8l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.uploaded_file
    ADD CONSTRAINT fkjfokvdq226ae1srxl2eaw3h8l FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4783 (class 2606 OID 16495)
-- Name: category fknbi9umnlfmtbpd3kcs8o37ta3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fknbi9umnlfmtbpd3kcs8o37ta3 FOREIGN KEY (created_by) REFERENCES public.users(id);


--
-- TOC entry 4787 (class 2606 OID 16538)
-- Name: product fkstb290bdq1jf21dnnc91ap27p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkstb290bdq1jf21dnnc91ap27p FOREIGN KEY (created_by) REFERENCES public.users(id);


-- Completed on 2025-07-24 16:05:51

--
-- PostgreSQL database dump complete
--

