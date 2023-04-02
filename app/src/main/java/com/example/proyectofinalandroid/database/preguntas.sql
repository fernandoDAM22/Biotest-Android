-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-03-2023 a las 10:34:07
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `preguntas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(10) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Mamiferos', 'En esta categoria encontraras preguntas sobre los diferentes animales mamiferos en el planeta'),
(2, 'Insectos y aracnidos', 'En esta categoria encontraras preguntas sobre los insectos, aracnidos e invertebrados'),
(3, 'Anbifios', 'en esta categoria encontraras preguntas sobre anfibios'),
(4, 'Animales marinos', 'en esta categoria encontraras preguntas sobre animales marinos'),
(5, 'Aves', 'en esta categoria encontraras preguntas sobre aves'),
(6, 'Cuerpo Humano', 'en esta categoria encontraras preguntas sobre el cuerpo humano'),
(7, 'Plantas', 'en esta categoria encontraras preguntas sobre plantas'),
(8, 'Setas y hongos', 'en esta categoria encontraras preguntas sobre setas y hongos'),
(9, 'Reptiles', 'En esta categoria encontraras preguntas sobre reptiles'),
(56, 'prueba', 'categoria de prueba'),
(57, 'php', 'categoria php');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuestionarios`
--

CREATE TABLE `cuestionarios` (
  `id` int(10) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `id_categoria` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuestionarios`
--

INSERT INTO `cuestionarios` (`id`, `nombre`, `descripcion`, `id_categoria`) VALUES
(1, 'Examen Mamiferos', 'Examen de los mamiferos para tercero de la eso', 1),
(12, 'Cuestionario modificado', 'Cuestionario de prueba', 56);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE `partidas` (
  `id` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `puntuacion` int(10) DEFAULT NULL,
  `id_usuario` int(10) DEFAULT NULL,
  `tipo_partida` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Disparadores `partidas`
--
DELIMITER $$
CREATE TRIGGER `eliminar_guiones` BEFORE INSERT ON `partidas` FOR EACH ROW BEGIN
   SET NEW.tipo_partida = REPLACE(NEW.tipo_partida, '_', ' ');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(10) NOT NULL,
  `enunciado` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `respuesta_correcta` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `respuesta_incorrecta1` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `respuesta_incorrecta2` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `respuesta_incorrecta3` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_categoria` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`id`, `enunciado`, `respuesta_correcta`, `respuesta_incorrecta1`, `respuesta_incorrecta2`, `respuesta_incorrecta3`, `id_categoria`) VALUES
(613, '¿Cual es el mamifero mas grande del mundo?', 'Ballena azul', 'Elefante', 'Rinoceronte', 'Hipopotamo', 1),
(614, 'Perro que puede alcanzar los 70 Kilometros por hora', 'Galgo', 'Doberman', 'Bulldog', 'Gran Danes', 1),
(615, 'Primate que tiene el pelo rojizo', 'Orangutan', 'Gorila', 'Chimpance', 'Capuchino', 1),
(616, 'Lugar que da nombre a una especie de tigre', 'Sumatra', 'Komodo', 'Pekin', 'Tasmania', 1),
(617, 'Animal que Hiberna', 'Liron', 'Elefante', 'Lobo', 'Girafa', 1),
(618, 'Animal que duerme de pie y tumbado', 'Caballo', 'Ciervo', 'Oso', 'Leon', 1),
(619, 'Es el principal depredador del la jirafa', 'Leon', 'Guepardo', 'Lince', 'Jaguar', 1),
(620, 'Principal alimento del lince iberico', 'Conejo', 'Perdiz', 'Roedores', 'Come de todo', 1),
(621, 'Este mamifero es capaz de reconocerse en un espejo', 'Elefante', 'Perro', 'Caballo', 'Suricata', 1),
(622, 'Tiene habitos nocturnos', 'Lemur', 'Gorilla', 'Gacela', 'Caballo', 1),
(623, 'Es buen nadador', 'Jabali', 'Rinoceronte', 'Toro', 'Canguro', 1),
(624, 'Lleva a su hijo en una bolsa', 'Diablo de tasmania', 'Erizo', 'Lemur', 'Ornitorrinco', 1),
(625, 'Es un animal carnivoro', 'Comadreja', 'Castor', 'Ciervo', 'Oveja', 1),
(626, '¿Cómo se denominan las crías de lobo?', 'Lobatos', 'Lobizos', 'Lobeznos', 'Lobinos', 1),
(627, 'Es el unico mamifero que vuela', 'Murcielago', 'Ardilla', 'Coyote', 'Suricato', 1),
(628, 'Unico mamifero que pone huevos y ademas es venenoso', 'Ornitorrinco', 'Oso hormiguero', 'Solenodonte', 'No existe ninguno', 1),
(629, 'Es el mamimero mas lento del mundo', 'Perezoso', 'Koala', 'Manati', 'Tortuga', 1),
(630, '¿Cuál es el mamífero terrestre más grande conocido?', 'Elefante africano', 'Rinoceronte', 'Jirafa', 'Oso polar', 1),
(631, 'Este mamifero es ciego', 'Topo', 'Raton comun', 'Conejo', 'Zorro', 1),
(632, '¿A qué edad alcanzan la madurez las crías de elefante?', '12-15 años', '5-10 años', '2 años', '20 años', 1),
(633, '¿Qué mamifero siempre cae de pie?', 'Gato', 'Perro', 'Zorro', 'Lobo', 1),
(634, 'Mamífero de gran tamaño parecido a un toro, con el cuerpo recubirto de abundante pelo', 'Yak', 'Bisonte', 'Ñu', 'Zebra', 1),
(635, 'Cría del jabalí', 'Jabato', 'Jabali', 'jabillo', 'Ninguna es correcta', 1),
(636, 'Animal que se parece mucho al leopardo', 'Jaguar', 'Tigre', 'Gepardo', 'Leon', 1),
(637, 'Mamífero marsupial arborícola parecido a un oso pequeño, que vive en los eucaliptos', 'Koala', 'Perezoso', 'Liron', 'Ardilla voladora', 1),
(638, '¿De que se alimenta el oso panda?', 'De bambu', 'De roedores', 'Vegetacion', 'Frutos', 1),
(639, '¿De que se alimentan las crias de los mamiferos?', 'Leche', 'Hierba', 'Fruta', 'Todas son correctas', 1),
(640, '¿Donde viven la mayor parte de los mamiferos?', 'Tierra', 'Mar', 'Aire', 'Ninguna es correcta', 1),
(641, '¿Cual es un animal mamifero?', 'Ballena', 'Tiburon blanco', 'Tortuga', 'Pez payaso', 1),
(642, 'Del orden de los mamiferos los mas abundantes son', 'los quirópteros', 'Los herbívoros', 'Los carnívoros', 'los primates', 1),
(643, 'La piel de la mayoria de los mamiferos esta recubierta por', 'Pelo', 'Escamas', 'Plumas', 'Nada', 1),
(644, 'Los mamiferos son de sangre', 'Caliente', 'Fria', 'Ambas', 'Ninguna', 1),
(645, 'Los mamíferos al igual que las aves son homeotermos porque', 'Tienen sangre caliente', 'Pueden volar', 'Tienen sangre fria', 'Son vertebrados', 1),
(646, 'Los mamiferos se caracterizan porque tienen:', 'Pelo y maman', 'Sangre fria y maman', 'Escamas y maman', 'Branquias y maman', 1),
(647, 'No es una caracteristica de los mamiferos', 'Tienen branquias', 'Tienen pelo', 'Son de sangre caliente', 'Tienen pulmones', 1),
(648, 'Cual de los siguientes mamiferos es un marsupial', 'Zorro fara', 'El murciélago', 'La ballena', 'El ornitorrinco', 1),
(649, 'Cual de los siguientes mamiferos es oviparo', 'Ornitorrinco', 'Zorro fara', 'Ballena', 'Murcielago', 1),
(650, '¿Cual es el principal habitat de los mamiferos?', 'Todos son correctos', 'Frios', 'Calidos', 'Templados', 1),
(651, '¿Como se llaman los mamiferos que comen de todo?', 'Omnivoros', 'Carnivoros', 'Heterotrofos', 'Autotrofos', 1),
(652, '¿Que simetria tienen los mamiferos?', 'Bilateral', 'Radial', 'Ambas', 'Ninguna', 1),
(653, 'La mayoria de mamiferos tienen', '4 patas', '6 patas', '2 patas', '8 patas', 1),
(654, 'Los mamiferos son', 'Todas son correctas', 'Acuaticos', 'Terrestres', 'Aereos', 1),
(655, 'La mayoria de mamiferos son', 'Viviparos', 'Oviparos', 'Viviparos y oviparos', 'Onoviparos', 1),
(656, '¿Que esqueleto tienen los mamiferos?', 'Interno', 'Externo', 'No tienen', 'Ambos', 1),
(657, '¿Donde habitan los mamiferos?', 'Todas son correctas', 'Bosques', 'Oceanos', 'Selvas', 1),
(658, '¿Cuantas horas duermen los koalas?', '20 - 22', '25 - 30', '15', '5', 1),
(659, 'La respiracion de los mamiferos se realiza a traves de', 'Pulmones', 'Branquias', 'La piel', 'Todas son correctas', 1),
(660, 'Los mamiferos cuya locomocion se da en las dos extremidades inferiores son', 'Bípedos', 'Cuadrupedos', 'Tridupedos', 'Sin locomocion', 1),
(661, 'A este animal se le considera mamifero acuatico', 'Todas son correctas', 'Nutria', 'Delfin', 'Ballena', 1),
(662, 'Este mamifero es uno del las principales presas del aguila', 'Conejo', 'Cabra', 'Nutria', 'Oveja', 1),
(664, 'Ave de la familia del cuervo', 'Urraca', 'Avaturda', 'Grulla', 'Loro', 5),
(665, 'Es un ave carroñera', 'Quebrantahuesos', 'Pinguino', 'Garza', 'Gorrion', 5),
(666, 'El sonido que emite es el Arrullo', 'Paloma', 'Loro', 'Pavo', 'Cuervo', 5),
(667, '¿Cual es el ave mas rapida del mundo?', 'Halcon peregrino', 'Aguila dorada', 'Aguila real', 'Buho real', 5),
(668, '¿De que color tienen las plumas los flamencos al nacer?', 'Blanco', 'Rosado', 'Rojo', 'Amarillo', 5),
(669, '¿Cual es el ave mas grande del mundo?', 'Avestruz', 'Condor', 'Aguila real', 'Buitre leonado', 5),
(670, '¿Cual es el ave mas numerosa del planeta?', 'Gallina', 'Loro', 'Paloma', 'Gorrion', 5),
(671, '¿Cual de estas aves es un ave carroñera', 'Buitre', 'Buho', 'Aguila', 'Halcon', 5),
(672, '¿Cuanto mide aproximadamente el pico de un tucan?', '20 cm', '10 cm', '15 cm', '25 cm', 5),
(673, 'Es un ave nocturna', 'Lechuza', 'Tortola', 'Gallina', 'Garza', 5),
(674, 'Esta considerado el ave mas pequeña del mundo', 'Colibri zunzuncito', 'Colibri pardo', 'Colibri orejimorado', 'Canario', 5),
(675, 'Las aves se caracterizan por', 'Tener huesos huecos', 'Por tener sacos terréreos.', 'Ser de sangre fria', 'Su pico es flexible', 5),
(676, 'Las aves son animales ', 'Ovíparos', 'Ovovivíparos', 'Vivíparos', 'Ninguna es correcta', 5),
(677, 'Cuando el avestruz se siente amezadado...', 'Esconde la cabeza', 'Abre las alas', 'Emite sonidos agudos', 'Se va volando', 5),
(678, 'Este ave puede soportar temperaturas extremas', 'Pingüino', 'Gaviota', 'Aguila imperial', 'Buho real', 5),
(679, 'Este ave destaca por tener un saco en el pico para guardar peces', 'Pelicano', 'Pingüino', 'Gaviota', 'Aguila pescadora', 5),
(680, 'Al llegar el invierto este ave llega a España desde los paises del norte', 'Grulla', 'Tortola', 'Cernicalo', 'Lechuza', 5),
(681, 'Ave rapaz que se alimenta fundamentalmente de huesos', 'Quebrantahuesos', 'Martin pescador', 'Avutarda', 'Buho real', 5),
(682, '¿Qué sonido hacen las gallinas?', 'Cacareo', 'Arrullo', 'Casteñeo', 'Ninguna es correcta', 5),
(683, 'Ave parecida al avestruz, pero más pequeña y con tres dedos en cada pie.', 'Ñandu', 'Grulla', 'Garza', 'Cigüeña', 5),
(684, 'Pajaro de color pardo, pico delgado y que vuela rapido', 'Vencejo', 'Abejaruco', 'Colibri', 'Tortola', 5),
(685, 'El aguila es un ave', 'Silvestre', 'Domestica', 'Carroñera', 'Ninguna es correcta', 5),
(686, 'La reproduccion de las aves es', 'Ovipara', 'Vivipara', 'Ambas', 'Ninguna es correcta', 5),
(687, 'Este no vuela', 'Todas son correctas', 'Gallina', 'Pinguino', 'Avestruz', 5),
(688, '¿Como es el esqueleto de las aves?', 'Interno', 'Externo', 'Ambos', 'No tienen', 5),
(689, '¿Que significa que las aves tienen cuerpo fusiforme?', 'Es aerodinamico', 'Es irregular', 'Esta deformado', 'Todas son correctas', 5),
(690, '¿Como se denominan las extremidades anteriores de las aves?', 'Alas', 'Patas', 'Pico', 'Todas son correctas', 5),
(691, '¿Cual de estas aves sufre metamorfosis?', 'Ninguna', 'Canario', 'Aguila', 'Lechuza', 5),
(692, '¿Que tipo de respiracion tienen las aves?', 'Pulmonar', 'Branquial', 'Cutanea', 'Todas son correctas', 5),
(693, 'Las plumas de las aves protegen del ', 'Frio y agua', 'Depredadores', 'Rayos gamma', 'Ninguna es correcta', 5),
(694, 'Las aves cantoras son', 'Paseriformes', 'Estrigiformes', 'Psitaciformes', 'Todas son correctas', 5),
(695, '¿Cual es la funcion de los sacos aereos de las aves?', 'Restar peso', 'Dar fuerza', 'Dar aerodinamica', 'Almacenar comida', 5),
(696, '¿Cuantas veces mudan las aves las plumas al año?', 'Dos', 'Una', 'Ninguna', 'Cuatro', 5),
(697, 'La alimentacion de las aves es', 'Todas son correctas', 'Carnívora', 'Granívora', 'Insectívora', 5),
(698, 'Las aves que se alimentan de semillas son', 'Granívoros', 'Carnívoros', 'Todas son correctas', 'Insectívoros', 5),
(699, 'Las aves que se alimentan de insectos son', 'Insectívoros', 'Carnívoros', 'Todas son correctas', 'Granívoros', 5),
(700, 'Las aves que se alimentan de presas son', 'Carnívoros', 'Insectívoros', 'Todas son correctas', 'Granívoros', 5),
(701, 'La mayoria de las aves son', 'Endotermos', 'Ecotermos', 'Ambos', 'Ninguna es correcta', 5),
(702, '¿Todas las aves tienen alas?', 'Si', 'No', 'Solo las que vuelan', 'Solo las que no vuelan', 5),
(703, '¿Que ave construye nidos de barro?', 'Golondrina', 'Abejaruco', 'Herrerillo', 'Vencejo', 5),
(704, 'Este ave es famosa por coger y esconder objetos brillantes', 'Urraca', 'Oropendola', 'Carbonero', 'Verderon', 5),
(705, 'Pasa su vida en el aire a excepcion de cuando esta en el nido', 'Vencejo', 'Golondrina', 'Carricero', 'Verdecillo', 5),
(706, 'Esta ave se alimenta de peces', 'Todas son correctas', 'Gaviota', 'Martin pescador', 'Aguila pescadora', 5),
(707, '¿Cuantos huevos pone el buitre leonado?', 'Uno', 'Dos ', 'Tres', 'Cuatro', 5),
(708, '¿A que grupo de aves pertenece el halcon?', 'Rapaz', 'Palmipeda', 'Trepadora', 'Ninguna es correcta', 5),
(709, '¿Que tipo de ave es un loro?', 'Prensora', 'Corredora', 'Trepadora', 'Nadadora', 5),
(710, 'Las aves con alas cortas que solo les permiten vuelos breves son', 'Gallináceas', 'Rapaces', 'Corredoras', 'Trepadoras', 5),
(711, 'Son aquellas aves que agujeran los arboles', 'Trepadoras', 'Gallináceas', 'Rapaces', 'Prensoras', 5),
(712, '¿Que aparato les facilita el vuelo a las aves?', 'Sacos aereos', 'Saco toracico', 'Saco cervical', 'Saco interclavicular', 5),
(713, 'Lo aprovechan las aves para volar', 'Corrientes de aire', 'Clima', 'Hierba', 'Temperatura', 5),
(715, 'Insecto sagrado en el antiguo egipto', 'Escarabajo', 'Libelula', 'Saltamontes', 'Cucaracha', 2),
(716, 'Producen la miel', 'Abeja', 'Avispa', 'Mosca', 'Chicharra', 2),
(717, 'Las arañas producen la seda en', 'Abdomen', 'Patas', 'Boca', 'Ojos', 2),
(718, '¿Cuantos pares de patas tienen los insectos?', 'Tres', 'Cuatro', 'Cinco', 'Seis', 2),
(719, '¿Como se llama el macho de las abejas', 'Zangano', 'Abejon', 'Abejaruco', 'Abeja rey', 2),
(720, 'Insecto que mas muertes humanas provoca', 'Mosquito', 'Cucaracha', 'Pulga', 'Libelula', 2),
(721, '¿Cuantos ojos tiene una libelula', 'Dos', 'Tres', 'Cuatro', 'Cinco', 2),
(722, 'Los insectos carecen de ', 'Esqueleto', 'Organos', 'Oidos', 'Patas', 2),
(723, '¿Cual es el insecto mas grande del mundo', 'Escarabajo goliat', 'Escarabajo pelotero', 'Escarabajo rinoceronte', 'Escarabajo titan', 2),
(724, 'Insecto que produce luz', 'Luciernaga', 'Mosquito', 'Mosca', 'Avispa', 2),
(725, '¿Cual de estos es un insecto?', 'Mariposa', 'Escorpion', 'Araña', 'Lombriz', 2),
(726, '¿En qué parte del toráx se encuentra el primer par de alas?', 'Mesotórax', 'Metatórax', 'Protórax', 'Cefalotorax', 2),
(727, 'Los insectos terestres respiran por', 'Sistema traqueal', 'Branquias', 'Pulmones', 'Difusion', 2),
(728, 'Esta araña esta considera la mas venenosa del mundo', 'Araña bananera', 'Araña violinista', 'Viuda negra', 'Tarantula goliat', 2),
(729, 'Por su gran tamaño esta araña puede llegar a comer pajaros', 'Tarantula goliat', 'Tarantula cebra', 'Araña pollito', 'Tarantula de anillos rojos', 2),
(730, '¿Cuantas patas tiene un insecto?', 'Seis', 'Ocho', 'Diez', 'Cuatro', 2),
(731, '¿Cuantos pares de antenas tienen los insectos?', 'Uno', 'Dos', 'Tres', 'Ninguno', 2),
(732, 'Este insecto se caracteriza por comer madera', 'Termita', 'Hormiga roja', 'Saltamontes', 'Cienpies', 2),
(733, '¿A que grupo pertenecen los insectos?', 'Artrópodos', 'Miriápodos', 'Moluscos', 'Equinodermos', 2),
(734, 'El exoesqueleto de los insectos esta formado por', 'Quitina', 'Queratina', 'Ladrillos', 'Calcio', 2),
(735, 'Los insectos que se comen a otros son', 'Depredadores', 'Coleopteros', 'Voladores', 'No existen', 2),
(736, '¿Dónde tienen el sentido del gusto las moscas?', 'En las patas', 'En la trompa', 'En las alas', 'En la piel', 2),
(737, '¿Cuántas alas tiene una pulga?', 'Ninguna', 'Una', 'Dos', 'Cuatro', 2),
(738, 'Gusano muy largo de color rojizo que se alimenta de sustancias que hay en la tierra', 'Lombriz', 'Serpiente', 'Solitaria', 'Escolopendra', 2),
(739, 'Este insecto produce la miel', 'Abeja', 'Avispa ', 'Hormiga ', 'Termita', 2),
(740, 'La mariposa antes de serlo es una', 'Oruga', 'Mosca', 'Larva', 'Gusano', 2),
(741, 'Este animal solo puede picar una vez puesto que muere al hacerlo', 'Abeja', 'Avispa', 'Escorpion', 'Hormiga bala', 2),
(742, 'Este insecto vive en el agua', 'Ninguna es correcta', 'Chicharra', 'Saltamontes', 'Mariposa', 2),
(743, '¿Cuantas picaduras de avispas son necesarias para matar a un humano?', '500', '100', '800', '25', 2),
(744, 'Es una especia invasora en España', 'Avisa asiatica', 'Mosca', 'Araña violinista', 'Mantis religiosa', 2),
(745, '¿Cuantas patas tienen las lombrices?', 'Ninguna', 'Dos', 'Cuatro', 'Una', 2),
(746, 'A que orden pertenecen las avispas', 'Hymenopteras', 'Siphonapteras', 'Hemipteros', 'Coleopteros', 2),
(747, '¿Cual de estas caracteristicas no pertenecen a los insectos?', 'Esqueleto interno', 'Tienen 6 patas', 'Tienen alas', 'Ninguna es correcta', 2),
(748, '¿En que parte del torax se encuentra el primer par de alas de los insectos?', 'Mesotórax', 'Metatórax', 'Protórax', 'Cefalotorax', 2),
(749, 'Los insectos terrestres respiran por', 'Sistema traqueal', 'Pulmones', 'Branquias', 'Difusion', 2),
(750, 'Las patas delanteras de la mantis religiosa son', 'Raptoras', 'Nadadores', 'Corredoras', 'Excavadoras', 2),
(751, 'Los insectos son los animales mas', 'Numerosos', 'Peligrosos', 'Ninguna', 'Ambas', 2),
(752, '¿Por que sustancia se compone el esqueleto de los insectos?', 'Quitina', 'Queratina', 'Creatina', 'Ninguna es correcta', 2),
(753, 'La boca de los insectos puede ser', 'Chupadora y Masticadora', 'Grande o pequeña', 'Chupadora', 'Masticadora', 2),
(754, 'Las antenas de los insectos tienen la funcion de captar', 'Vibraciones y feromonas', 'Captar feromonas', 'Captar vibraciones', 'Atraer presas', 2),
(755, 'En el torax del insecto se encuentran las', 'Alas y patas', 'Patas', 'Boca y antenas', 'Alas', 2),
(756, '¿Cuantos pares de alas tienen los insectos?', 'Dos', 'Tres', 'Uno', 'Cuatro', 2),
(757, 'El sistema respieratorio de los aracnicos esta compuesto por', 'Traqueas y ocelos', 'Ocelos', 'Traqueas', 'Ocelos y pulmones', 2),
(758, 'Los escarabajos y mariquitas son', 'Coleópteros', 'Dermáteros', 'Neurópteros', 'Todas son correctas', 2),
(759, 'El pulgón, la chochinilla y la mosca blanca son', 'Homópteros', 'Coleópteros', 'Himenópteros', 'Ninguna es correcta', 2),
(760, 'Las mariposas y polillas son', 'Lepidópteros', 'Hompóteros', 'Dípteros', 'Todas son correctas', 2),
(761, '¿Cuantos ojos tiene una araña?', 'Ocho', 'Cuatro', 'Dos', 'Seis', 2),
(762, '¿Cual no es una caracteristica de las arañas?', 'Tienen antenas', 'Tienen 8 patas', 'Son carnivoras', 'Todas son venenosas', 2),
(763, '¿De que se alimentan las arañas?', 'Insectos', 'Frutos', 'Semillas', 'Hierba', 2),
(764, 'Nombre común de la seda generada y utilizada por las arañas para cazar', 'Telaraña', 'Seda cazadora', 'Redecilla', 'Ninguna es correcta', 2),
(765, 'Se caracteriza por la piel cubierta de verrugas', 'Sapo', 'Salamandra', 'Triton', 'Rana', 3),
(766, 'Nace herbirboro y se combierte en carnivoro', 'Sapo', 'Salamandra', 'Triton', 'Rana', 3),
(767, 'Es considerado el animal mas venenoso del mundo', 'Rana dardo dorada', 'Rana flecha azul', 'Rana arlequín', 'Rana roja', 3),
(768, 'Es el anfibio más grandes del mundo', 'Salamandra gigante', 'Sapo comun', 'Rana gigange', 'Lagarto canario', 3),
(769, '¿Todos los anfibios son venenosos?', 'No', 'Si', 'Las ranas si', 'Los sapos si', 3),
(770, '¿Para que utilizan la membrana que tienen entre los dedos', 'Para nadar mejor', 'No tiene funcion', 'Para protegerse del sol', 'Para cazar', 3),
(771, 'Los anfibios son', 'Oviparos', 'Viviparos', 'Ovovivíparos', 'Placentarios', 3),
(772, 'La mayoría de los anfibios adultos son...', 'Carnívoros', 'Omnívoros', 'Bichívoros', 'Herbívoros', 3),
(773, 'Los anfibios son', 'Vertebrados', 'Invertebrados', 'Ambos', 'Ninguna es correcta', 3),
(774, '¿Cuál no es un anfibio?', 'Lagartija', 'Sapo', 'Rana', 'Salamandra', 3),
(775, 'Sus extremidades son en forma de ', 'Pata', 'Ala', 'Cola', 'Pie', 3),
(776, 'Este anfibio es conocido por su gran capacidad de regeneracion', 'Ajolote', 'Rana flecha azul', 'Sapo ', 'Triton', 3),
(777, 'Que tipo de respiracion tienen los anfibios', 'Branquias – Pulmonar', 'Pulmonar', 'Cutanea', 'Branquias', 3),
(778, 'Las ranas adultas son', 'Carnivoras', 'Omnívoras', 'Herbívoras', 'Parásitas', 3),
(779, 'La piel de los anfibios puede presentar', 'Glandulas', 'Plumas', 'Escamas', 'Pelo', 3),
(780, 'La piel de los anfibios esta recubierta por', 'Nada', 'Pelo', 'Plumas', 'Escamas', 3),
(781, 'El esqueleto de los anfibios es', 'Interno', 'Externo', 'No tienen', 'Ninguna es correcta', 3),
(782, '¿El sistema sanguineo de los anbifios regula su temperatura?', 'Si', 'No  ', 'Solo de las ranas', 'Ninguna es correcta', 3),
(783, '¿Todas las ranas son venenosas?', 'No', 'Si', 'Solo las tropicales', 'Ninguna es correcta', 3),
(784, '¿Que simetria tienen los anfibios?', 'Bilateral', 'Radial', 'Bilateral y radial', 'No tienen', 3),
(785, 'Algunos anfibios poseen glandulas que producen', 'Veneno', 'Oxigeno', 'Movimiento', 'Mucosa', 3),
(786, '¿Cuantas extremidades tienen los amfibios?', 'Cuatro', 'Seis', 'Ocho', 'Dos', 3),
(787, 'Los anfibios habitan en biomas', 'Terrestres y humedos', 'Acuaticos', 'Secos ', 'En el aire', 3),
(788, 'Los anfibios ponen los huevos en', 'El agua', 'La tierra', 'Las plantas', 'En nidos', 3),
(789, 'La mayoria de los anfibios sufren', 'Metamorfosis', 'Enfermedades', 'Mudas de piel', 'Respiracion cutanea', 3),
(790, 'Los anfibios son de sangre', 'Fria', 'Caliente', 'Ambas', 'Ninguna es correcta', 3),
(791, 'La piel de los anfibios esta', 'Humeda', 'Seca', 'Agrietada', 'Rugosa', 3),
(792, 'Cuantas fases tiene la metaforfosis de los anfibios', 'Ninguna es correcta', 'Tres', 'Cuatro', 'Seis', 3),
(793, 'Donde nacen la mayoria de los anfibios', 'En el agua', 'En la tierra', 'En los arboles', 'Todas son correctas', 3),
(794, 'Para que utilizan los anfibios la menbrana que tienen entre los dedos', 'Para nadar', 'Para correr', 'Para trepar', 'No tiene funcion', 3),
(795, 'Esta especie de anfibio cuida de sus crias', 'Ninguna es correcta', 'Rana', 'Sapo ', 'Triton', 3),
(796, 'En la primera etapa de su vida los anfibios respiran fudamentalmente por', 'Branquias ', 'Pulmones', 'Piel', 'Patas', 3),
(797, '¿Cual no es un anfibio?', 'Lagartija', 'Rana', 'Salamandra', 'Sapo', 3),
(798, '¿Cual no es un anfibio?', 'Cocodrilo', 'Triton', 'Salamandra', 'Sapo', 3),
(799, '¿Como es la circulacion de los anfibios?', 'Doble y completa', 'Unica', 'Simple', 'No tienen', 3),
(800, '¿Que tienen los anfibios entre los dedos?', 'Membranas', 'Aletas', 'Nada', 'Musculo', 3),
(801, '¿Por que metodo respiran los renacuajos?', 'Branquias', 'Trequeas', 'Pulmones', 'Piel', 3),
(802, '¿Que sentidos tienen mas desarrollados los anfibios?', 'Vista y odio', 'Vista y gusto', 'Vista y olfato', 'Olfato', 3),
(803, '¿Cuantas especias de anfibios existen en el mundo aproximadamente?', '8000', '500', '100', '1000', 3),
(804, '¿En que continentes se pueden encontrar anbifios', 'Todas son correctas', 'Europa', 'Asia', 'America', 3),
(805, '¿Cual es el anfibio mas grande del mundo?', 'Salamandra china', 'Salamandra asiatica', 'Salamandra europea', 'Rana toro', 3),
(806, 'Algunas especias de este anfibio no sufren metamorfosis', 'Salamandra', 'Rana ', 'Sapo', 'Ninguna es correcta', 3),
(807, '¿Debido a que no pueden regular su temperatura corporal, los anfibios son?', 'Ectotermicos', 'Cutaneos', 'Endotermicos', 'Indirina', 3),
(808, 'Cuando se presentan en forma de larva, ¿como es la alimentacion de los anfibios?', 'Omnivora', 'Carnivora', 'Herbibora', 'Todas son correctas', 3),
(809, 'Este anfibio vive solo en la tierra', 'Salamandra', 'Rana ', 'Sapo', 'Ajolote', 3),
(810, '¿Cuantos grandes grupos de anfibios hay?', 'Dos', 'Tres', 'Uno', 'Cuatro', 3),
(811, '¿A que edad empiezan a poner huevos los anfibios?', 'Tres años', 'Un año', 'Cinco años', '10 años', 3),
(812, '¿Como se llaman los dos tipos prinicipales de anfibios?', 'Anuros y urodelos', 'Anuros y escamudos', 'Anuros y escamudos', 'Otra', 3),
(813, '¿Cuales son los principales tipos de anuros?', 'Ranas y sapos', 'Tritones', 'Salamandras y sapos', 'Ninguna es correcta', 3),
(814, '¿Que grupo de anfibios tienen cola?', 'Los urodelos', 'Los anuros', 'Los dos', 'Ninguno', 3),
(815, 'Pez que tiene la boca en forma de ventosa', 'Lamprea', 'Rape', 'Congrio', 'TIburon', 4),
(816, 'Es una especie de tiburon', 'Tintonera', 'Sirulo', 'Pez espada', 'Esturion', 4),
(817, 'Color que tiene la sangre del calamar', 'Azul', 'Roja', 'Blanca ', 'Negra', 4),
(818, 'El pulpo es', 'Molusco', 'Crustaceo', 'Pez', 'Ninguna de las anteriores', 4),
(819, 'Las agallas de un pez son las', 'Branquias', 'Aletas', 'Cola', 'Escamas', 4),
(820, 'La piraña vive en', 'America', 'Africa', 'Europa', 'Asia', 4),
(821, '¿Cuál de estas características NO es común a todos los peces?', 'Branquias', 'Escamas', 'Aletas', 'Línea lateral', 4),
(822, '¿Cuál de estos NO es un pez?', 'Orca', 'Caballito de mar', 'Tiburón ballena', 'Saltarín del Fango', 4),
(823, 'Los peces tienen....... para poder protegerse', 'Escamas', 'Aletas', 'Pelo', 'Dientes', 4),
(824, 'Los peces son', 'Oviparos', 'Viviparos', 'Ovovivíparos', 'Ninguna es correcta', 4),
(825, 'De que se alimenta el tiburon ballena', 'Plancton', 'Peces pequeños', 'Focas', 'Algas marinas', 4),
(826, 'Este animal marino es un mamifero', 'Delfin', 'Tiburon', 'Pulpo', 'Pez payaso', 4),
(827, 'Esta considerada la medusa mas venesa del mundo', 'Medusa de caja', 'Carabela portuguesa', 'Aurelia aurita', 'Medusa aguamala', 4),
(828, 'Cual de estos animales no es un animal marino', 'Gaviota', 'Cangrejo', 'Delfin', 'TIburon', 4),
(829, 'Este tiburon puede vivir tanto en agua dulce como agua salada', 'Tiburon toro', 'Tiburon blanco', 'Tiburon martillo', 'Tiburon ballena', 4),
(830, 'Cuantos tentaculos tiene el pulpo', 'Ocho', 'Seis', 'Diez', 'Doce', 4),
(831, 'Este pez se infla para aumentar su tamaño como metodo de defensa', 'Pez globo', 'Medusa', 'Pez payaso', 'Sepia', 4),
(832, '¿Que pez tiene una gran variedad de especies, y además, solo viven en corales?', 'Peces Mariposa', 'Rayas', 'Medusas', 'Tiburones', 4),
(833, '¿Para que usan la electricidad las anguilas electricas?', 'Paralizar a sus presas', 'Defenserse', 'Atraer otras anguilas', 'Calentarse', 4),
(834, '¿Para qué sirven los orificios de las ballenas?', 'Para respirar', 'Para beber', 'Para nada', 'Para comer', 4),
(835, '¿Qué es la estrella de mar?', 'Equinodermo', 'Molusco', 'Pez', 'Cefalopodo', 4),
(836, 'Pez marino que tiene una especie de ventosa en la cabeza con la que se fija a otros peces', 'Remora', 'Rape', 'Calamar', 'Almeja gigante', 4),
(837, 'La trucha es un animal de agua', 'Dulce', 'Salada', 'Ambas', 'Ninguna', 4),
(838, '¿Cual de estos no es un pez?', 'Delfin', 'Caballito de mar', 'Tiburon ballena', 'Salmon', 4),
(839, '¿Los peces son vertebrados?', 'Si', 'No', 'Algunos', 'Ninguna es correcta', 4),
(840, 'Los peces son animales de sangre', 'Fria', 'Caliente', 'Ambas', 'No tienen sangre', 4),
(841, 'Los peces son animales de sangre', 'Oviparos', 'Viviparos', 'Ambos', 'Ninguna es correcta', 4),
(842, 'La mayoria de los peces son', 'Carnivoros', 'Herbivoros', 'Omnivoros', 'Todas son correctas', 4),
(843, 'La piel de los peces esta recubierta por', 'Escamas', 'Pelo ', 'Plumas ', 'Nada', 4),
(844, 'El esqueleto de los tiburones esta formado por', 'Cartilago', 'Huesos', 'Queratina', 'Calcio', 4),
(845, '¿Que simetria tienen los peces', 'Bilateral', 'Radial', 'Ambas', 'Ninguna', 4),
(846, '¿Cual de estos animales marinos es venenoso?', 'Todas son correctas', 'Plez globo', 'Pez Roca', 'Mantarraya', 4),
(847, '¿Que clase de animal es el cangrejo?', 'Crustaceo', 'Aracnido', 'Pez', 'Mamifero', 4),
(848, '¿Cual es la rama de la zoología que se encarga específicamente de estudiar los peces?', 'Ictiologia', 'Biologia', 'Botanica', 'Naturaleza', 4),
(849, 'Los ojos de los peces caracen de', 'Parpado', 'Pupila', 'Cornea', 'Retina', 4),
(850, '¿De que se alimentan los peces herbivoros?', 'Algas y plantas', 'Otros peces', 'Plancton', 'Organismos vivos', 4),
(851, 'De que se alimentan los peces carnivoros', 'Todas son correctas', 'Placnton', 'Organismos vivos', 'Otros peces', 4),
(852, '¿Como se llaman los peces que se alimentan de restos y desperdicios de otros peces?', 'Detritívoros', 'Carnívoros', 'Omnívoros', 'Herbívoros', 4),
(853, '¿Cuantos tipos de escamas hay en los peces?', 'Cuatro', 'Tres', 'Dos', 'Una', 4),
(854, '¿Que forma tienen los peces?', 'Fusiforme', 'Sin forma definida', 'Alargada', 'Elipse', 4),
(855, '¿Que detecta la linea lateral que tienen los peces?', 'Movimiento', 'Presas', 'Luz', 'Olores', 4),
(856, '¿Que regula la vejiga natatoria de los peces?', 'Su flotabilidad', 'Su peso', 'Su velocidad', 'Su tamaño', 4),
(857, '¿Que tipo de peces son las mantas?', 'Cartilaginosos', 'Oseos', 'Escamosos', 'Ninguna es correcta', 4),
(858, 'Los peces abisales producen luz en una', 'Antena', 'Aleta', 'Escama', 'Ninguna es correcta', 4),
(859, '¿Los peces cartilaginosos tienen costillas?', 'No', 'Si', 'Algunos', 'Ninguna es correcta', 4),
(860, '¿Cual es el sentido mas desarrollado de los peces cartilaginosos?', 'Olfato', 'Gusto', 'Oido', 'Vista', 4),
(861, 'No todos los peces tienen', 'Todas son correctas', 'Dientes', 'Branquias', 'Escamas', 4),
(862, '¿Como es el cuerpo de los moluscos?', 'Blando', 'Duro', 'Con escamas', 'Con pelo', 4),
(863, '¿Para que sirve el sifon de los calamares?', 'Para propulsarse', 'Para comer', 'Para cazar', 'No tiene funcion', 4),
(864, '¿Como son los cefalopodos?', 'Carnivoros', 'Herbivoros', 'Granivoros', 'Omnivoros', 4),
(865, 'Es la serpiete  mas venenosa del mundo', 'Taipan del interior', 'Mamba negra', 'Cobra real', 'Vibora cornuda', 9),
(866, 'Es el reptil mas grande del mundo', 'Cocodrilo de agua salada', 'Caiman', 'Lagarto', 'Dragon de komodo', 9),
(867, '¿Cual no es un reptil', 'Rana', 'Caiman', 'Serpiente ', 'Lagartija', 9),
(868, 'Este reptil puede llegar a vivir 200 años', 'Tortuga', 'Cocodrilo', 'Lagarto', 'Camaleon', 9),
(869, 'Los reptiles tienen la piel recubierta de ', 'Escamas', 'Pelo', 'Plumas', 'Glandulas', 9),
(870, 'Es el reptil mas pequeño del mundo', 'Nanocamaleón', 'Lagartija enana', 'Culebra de escalera', 'Rana enana', 9),
(871, 'Los camaleones tiene la caracteristica de', 'Cambiar de color', 'Nadar', 'Volar', 'Ninguna de las anteriores', 9),
(872, 'Este reptil dispara chorros de sangre por los ojos para defenderse', 'Lagarto cornudo', 'Lagarto comun', 'Cobra de erradura', 'Galapago', 9),
(873, 'Los reptiles son', 'Oviparos', 'Viviparos', 'Ovoviviparos', 'Ninguna de las anteriores', 9),
(874, '¿Cual es la serpiente mas rapida del mundo?', 'Mamba negra', 'Serpiente coral', 'Serpiente crai', 'Serpiente de cascabel', 9),
(875, 'Selecciona la afirmacion correcta sobre los reptiles', 'Son de sangre fria', 'Todos son venenosos', 'Todos son terrestres', 'Todos son carnivoros', 9),
(876, '¿Donde habita el dragon de komodo?', 'Indonesia', 'Mexico', 'España', 'Rusia', 9),
(877, 'Este reptil es herviboro', 'Iguana', 'Lagarto comun', 'Lagartija', 'Caiman', 9),
(878, '¿Este serptiente emite un sonido con la cola cuando se sienta amenazada?', 'Serpiete cascabel', 'Cobra real', 'Alicante', 'Falsa coral', 9),
(879, '¿Qué reptil puede correr sobre el agua?', 'Lagarto basilisco', 'Serpiente acuatica', 'Lagarto comun', 'Varano', 9),
(880, '¿A cuántas personas podría matar un solo gramo de veneno de la cobra?', '150', '20', '200', '100', 9),
(881, '¿Dónde llevan los cocodrilos a sus crías?', 'En la boca', 'En la cola', 'En la espalda', 'En las patas', 9),
(882, 'Esta serpiente no es venesona', 'Ninguna es correcta', 'Vibora russell', 'Cobra real', 'Serpiente coral', 9),
(883, '¿Cual no es un reptil?', 'Ninguna es correcta', 'Serpiente', 'Lagarto', 'Caiman', 9),
(884, '¿Cual no es un reptil?', 'Todas son correctas', 'Rana', 'Triton', 'Sapo', 9),
(885, '¿Como es la sangre de los reptiles?', 'Fria', 'Caliente', 'Ambas', 'Ninguna', 9),
(886, '¿Como son los huevos de los reptiles?', 'Duros', 'Blandos', 'Ambos', 'Ninguno', 9),
(887, '¿Cual es la serpiente mas venenosa de los Estados unidos?', 'Serpiente cascabel', 'Mamba negra', 'Mocasin de agua', 'Cobra de anteojos', 9),
(888, 'La mayoria de reptiles son', 'Carnivoros', 'Omnivoros', 'herbivoros', 'Todas son correctas', 9),
(889, '¿La mayoria de los reptiles cuidan de sus crias?', 'No', 'Si', 'Solo las tortugas', 'Ninguna es correcta', 9),
(890, '¿Por donde respiran los reptiles?', 'Pulmores', 'Branquias', 'Por la piel', 'Ninguna es correcta', 9),
(891, '¿Cuantos veces al año mudan la piel las serpientes adultas?', 'Dos - tres', 'Una', 'Cuatro', 'Seis', 9),
(892, '¿Cuantas extremidades tienen las serptientes?', 'Ninguna', 'Una', 'Dos', 'Cuatro', 9),
(893, '¿Cual es la serpiente mas grande del mundo?', 'Piton reticulada', 'Anaconda', 'Cobra real', 'Alicante', 9),
(894, '¿Cual de estos animales es un reptil?', 'Todas son correctas', 'Tortuga', 'Lagartija', 'Iguana', 9),
(895, '¿Cual de estos reptiles no tiene patas?', 'Todas son correctas', 'Anaconda', 'Hilo de Barbados', 'Serpiente', 9),
(896, '¿Cuantos huesos tienen aproximadamente las serpientes?', '400', '1000', '100', '800', 9),
(897, 'Algunos reptiles sueltan su cola para', 'Escapar de un depredador', 'Correr mas', 'Por accidente', 'Ninguna es correcta', 9),
(898, '¿Donde habitan los reptiles?', 'Todas son correctas', 'Tierra', 'Agua', 'Arboles', 9),
(899, 'En que habitat no viven los reptiles', 'Aereo', 'Terrestre', 'Acuatico', 'Todas son correctas', 9),
(900, '¿Como se desplazan las serpientes?', 'Reptando', 'Saltando', 'Estirandose', 'Ninguna es correcta', 9),
(901, '¿Que no tienen los reptiles?', 'Branquias', 'Escamas', 'Cola', 'Oidos', 9),
(902, 'Las tortugas de agua tienen una alimentacion', 'Omnivora', 'Carnivora', 'Herbivora', 'Granivora', 9),
(903, '¿De que se alimentan los camaleones?', 'De insectos', 'De plantas', 'De peces', 'Todas son correctas', 9),
(904, '¿Como es la simetria de los reptiles?', 'Bilateral', 'Radial', 'Las dos', 'Ambas', 9),
(905, 'Los tipos de reptiles son quelonios, urodelos y escamosos', 'Falso', 'Verdadero', 'No se clasifican', 'Solo la primera', 9),
(906, 'Los quelonios tienen un caparazón de placas ...', 'Corneas', 'Lisas', 'Tubulares', 'Acuaticas', 9),
(907, '¿Que caracteristica no pertenece a los reptiles?', 'Son de sangre caliente', 'Algunos son venenosos', 'Tienen escamas', 'Algunos son acuaticos', 9),
(908, 'Uno de estos grupos no es de reptiles, ¿Cual es?', 'Urodelos', 'Saurios', 'Ofidios', 'Quelonios', 9),
(909, 'Los reptiles que no tienen extremidades se llaman', 'Ofidios', 'Quelonios', 'Saurios', 'Saurios', 9),
(910, '¿Como se denominan las serpientes que desarrollan los huevos en su interior?', 'Ovovivíparas', 'Ovíparas', 'Insectívoras', 'Vivíparas', 9),
(911, '¿Donde se meten los reptiles para enfriarse?', 'Madrigueras subterraneas', 'Arboles', 'En el agua', 'Ninguna es correcta', 9),
(912, '¿Que hacen los reptiles para calentarse?', 'Se ponen al sol', 'Se suben a un arbol', 'Corren', 'Se entierran', 9),
(913, '¿Como son los pulmones de los reptiles?', 'Muy esponjosos', 'Muy duros', 'No tienen', 'Duros y esponjosos', 9),
(914, '¿Todas las iguanas y lagartijas son terrestres?', 'No', 'Si', 'Algunas son aereas', 'Muy pocas', 9),
(915, 'Tenemos solo uno en nuestro cuerpo', 'Bazo', 'Femur', 'Biceps', 'Clavicula', 6),
(916, '¿Cual es el hueso mas largo del cuerpo humano?', 'Femur', 'Clavicula', 'Escapula', 'Radio', 6),
(917, 'Va de la garganta al estomago', 'Esofago', 'Traquea', 'Laringe', 'Intestino', 6),
(918, '¿En que lugar del cuerpo se produce la insulina?', 'Pancreas', 'Duodeno', 'Riñon', 'Garganta', 6),
(919, 'Los musculos escalenos estan en', 'Cuello', 'Cara', 'Pies', 'Brazo', 6),
(920, 'La epiglotis es', 'Cartilago', 'Hueso', 'Vena', 'Cartilago', 6),
(921, 'Hueso humano que esta mas cerca del talon', 'Penore', 'Femur', 'Radio', 'Esternon', 6),
(922, 'Los pulmones pertenecen al', 'Sistema respiratorio', 'Sistema digestivo', 'Sistema nervioso', 'Sistema circulatorio', 6),
(923, 'No es un organo de nuestro cuerpo', 'La pelvis', 'El corazon', 'El cerebro', 'Los pumones', 6),
(924, 'Numero de huesos aproximados que tiene el cuerpo humano', '200', '300', '50', '150', 6),
(925, '¿Cual es la celula mas abundante en el cuerpo humano?', 'Neuronas', 'Plaquetas', 'Celulas Musculares', 'Adipocitos', 6),
(926, '¿Qué parte del cuerpo tiene 27 huesos y 35 músculos?', 'Mano', 'Cabeza', 'Codo', 'Pierna', 6),
(927, '¿Qué órgano es el encargado de fabricar insulina?', 'Pancreas', 'Estomago', 'Higado', 'Riñon', 6),
(928, '¿Cuántas vertebras forman la columna vertebral humana?', '33', '41', '28', '12', 6),
(929, '¿Cómo se llaman los vasos sanguíneos más finos?', 'Capilares', 'Venas', 'Arterias', 'Vasos', 6),
(930, '¿De qué sustrato se nutre principalmente el cerebro?', 'Glucosa', 'Proteina', 'Sacarosa', 'Lipidos', 6),
(931, '¿Con que otro nombre se conoce a la piel?', 'Epidermis', 'Dermis', 'Hipodermis', 'Corteza', 6),
(932, '¿Qué dedo de la mano sólo tiene dos falanges?', 'Pulgar', 'Anular', 'Corazon', 'Indice', 6),
(933, 'Enfermedad producida por la deficiencia de produccion de la insulina', 'Diabetes', 'Nefritis', 'Faringitis', 'Gripe', 6),
(934, '¿Qué vitamina ayuda a la fijación de calcio en los huesos?', 'Vitamina D', 'Vitamina A', 'Vitamina C', 'Vitamina B', 6),
(935, '¿A qué parte del corazón le afectan más los infartos?', 'Miocardio', 'Pericardio', 'Endocardio', 'Ventriculos', 6),
(936, '¿Qué es el esternocleidomastoideo? ', 'Un musculo', 'Un hueso', 'Un organo', 'Una tejido', 6),
(937, 'Los glóbulos rojos también se llaman...', 'Eritrocitos', 'Fagocitos', 'Emoglobina', 'Lipidos', 6),
(938, '¿Cuál es la sustancia deficiente en los diabéticos?', 'Insulina', 'Vitamina F', 'Emoglobina', 'Calcio', 6),
(939, '¿Cómo se llaman las lesiones en la pared del tubo digestivo?', 'Ulcera', 'Ampolla', 'Verruga', 'Ningua es correcta', 6),
(940, '¿Qué es el colesterol?', 'Un lipido', 'Una neurona', 'Un tejido', 'Ningua es correcta', 6),
(941, '¿Cómo se llama la torcedura de una articulación en el cuerpo?', 'Esguince', 'Fractura', 'Contractura ', 'Luxacion', 6),
(942, '¿De dónde forman parte los axones?', 'Neuronas', 'Globulos Rojos', 'Pulmones', 'Higado', 6),
(943, '¿Cómo llamamos a la inflamación de la piel?', 'Dermatitis', 'Quemadura', 'Verruga ', 'Varicela', 6),
(944, '¿Cómo se llama la arteria principal del cuerpo humano?', 'Aorta', 'Mesentérica', 'Femoral', 'iIliaca', 6),
(945, '¿En cuántos lóbulos se divide el pulmón derecho?', 'Tres', 'Dos', 'Uno', 'Cinco', 6),
(946, '¿Cuál es el músculo causante del espasmo muscular Torticolis?', 'Esternocleidomastoideo', 'Biceps', 'Muslo', 'Trapecio', 6),
(947, '¿Qué articulación une el fémur con la tibia? ', 'Rodilla', 'Codo', 'Hombro', 'Talon', 6),
(948, '¿Qué órgano del cuerpo es el más dañado por el consumo excesivo de alcohol?', 'Higado', 'Riñon', 'Estomago', 'Pulmones', 6),
(949, 'Conducto por donde va la sangre desde el corazón a las demás partes del cuerpo', 'Arteria', 'Vena', 'Vaso', 'Tubo', 6),
(950, 'Regula la actividad de los órganos internos', 'Bulbo raquideo', 'Cerebro', 'Corazon', 'Medula espinal', 6),
(951, 'Movimiento de relajación del corazón.', 'Diastole', 'Sistole', 'Contraccion', 'Ninguna es correcta', 6),
(952, 'Órgano que  contiene las cuerdas vocales y produce los sonidos', 'Laringe', 'Traquea', 'Faringue', 'Pulmones', 6),
(953, '¿Cuales son las unidades basicas que forman nuestro cuerpo?', 'Celulas', 'Tejidos', 'Fibras', 'Organos', 6),
(954, '¿A donde va la sangre que sale del ventriculo derecho?', 'A los pumones', 'Al cerebro', 'A las piernas', 'Al abdomen', 6),
(955, '¿Como se llaman los conductos que unen las arterias con las venas?', 'Capilares', 'Uniones', 'Tubitos', 'Intersecciones', 6),
(956, '¿Por donde llega la comida al estomago?', 'Esofago', 'Traquea', 'Bronquios', 'Intestino', 6),
(957, '¿Cuantos sentidos tiene el cuerpo humano?', 'Cinco', 'Cuatro', 'Tres', 'Seis', 6),
(958, '¿Que glandula fabrica la tiroxina?', 'La tiroides', 'El hipotalamo', 'Los ganglios', 'Ninguna es correcta', 6),
(959, 'En que horgano reside la inteligencia?', 'Cerebro', 'Cerebelo', 'Medula espinal', 'Corazon', 6),
(960, '¿En que parte de la sangre se encuentra la hemoglobina?', 'Globulos rojos', 'Globulos blancos', 'Plaquetas', 'Plasma', 6),
(961, '¿Como se llaman las ramas en las que se divide la traquea?', 'Bronquios', 'Bronquiolos', 'Tuberias', 'Ninguna es correcta', 6),
(962, '¿Que dientes sirven para moler los alimentos?', 'Las muelas', 'Los colmillos', 'Los paletos', 'Ninguna es correcta', 6),
(963, '¿Como se llama la parte coloreada del ojo?', 'Iris', 'Retina', 'Pupila ', 'Cornea', 6),
(964, '¿Que se almacena en la besicula biliar?', 'Bilis', 'Saliva', 'Sangre', 'Nutrientes', 6),
(965, '¿Que representan los anillos de los arboles', 'Los años', 'Horas', 'Dias', 'Meses', 7),
(966, '¿Sus flores solo se abren por la noche?', 'Baobad', 'Almendro', 'Naranjo', 'Cerezo', 7),
(967, 'Arbol mayoritario en el bosque mediterraneo español', 'Encina', 'Palmera ', 'Pino', 'Castaño', 7),
(968, 'Arbol del que se obtienen los piñones', 'Pino', 'Roble', 'Olivo', 'Higuera', 7),
(969, 'Fruto de la zarza', 'Mora', 'Frambuesa ', 'Aceituna', 'Granada', 7),
(970, 'Las plantas necesitan para vivir', 'Todas las anteriores', 'Agua', 'Luz', 'Aire', 7),
(971, 'Metodo que usa el cactus para defenderse', 'Espinas', 'Veneno', 'Sabor amargo', 'Ninguna es correcta', 7),
(972, 'Donde realizan las plantas la fotosintesis', 'Hojas', 'Raiz', 'Nervio', 'Tallo', 7),
(973, '¿Qué desprenden las plantas en la fotosíntesis?', 'Oxígeno', 'Dioxido de carbono', 'Axufre', 'Cloro', 7),
(974, 'Las plantas sin flores como el Helecho se reproducen por', 'Esporas', 'Frutos', 'Ramas', 'Semillas', 7),
(975, '¿Qué sustancia permite realizar la fotosíntesis?', 'Clorofila', 'Agua', 'Hidrogeno', 'Ninguna es correcta', 7),
(976, 'Planta de flores amarillas que se usa como condimento', 'Hinojo', 'Tomillo', 'Romero', 'Laurel', 7),
(977, 'Arbol grande con la corteza lisa y de color gris cuyo fruto es la nuez', 'Nogal', 'Almendro', 'Roble', 'Madroño', 7),
(978, 'Fruta exótica con piel marrón y verde por dentro ', 'Kiwi', 'Mango', 'Platano', 'Papaya', 7),
(979, 'Fruta muy acida, de color amarillo', 'Limon', 'Naranja', 'Manzada', 'Lima', 7),
(980, '¿Que es la cereza?', 'Un fruto', 'Una flor', 'Una planta', 'Ninguna es correcta', 7),
(981, '¿Por donde toman las plantas el agua?', 'La raiz', 'El tronco', 'Las hojas', 'Las flores', 7),
(982, '¿Por donde respiran las plantas?', 'Las Hojas', 'Las raices', 'La corteza', 'Las flores', 7),
(983, 'El pino es un arbol de hoja', 'Perenne', 'Caduca', 'No tiene hojas', 'Ninguna es correcta', 7),
(984, '¿Como se llama el liquido que circula dentro de las plantas?', 'Savia', 'Nectar', 'Agua', 'Zumo', 7),
(985, '¿En que parte de la planta se forma el fruto?', 'Flor', 'Hojas', 'Ramas', 'Raices', 7),
(986, '¿En que parte de la flor se produce el polen?', 'Los estambres', 'La corona', 'Los petalos', 'El tallo', 7),
(987, '¿Que fruto da el castaño?', 'Castañas', 'Nueces', 'Almendras', 'No da frutos', 7),
(988, '¿Que nombre recibe el conjunto de petalos de una flor?', 'Corola', 'Corona', 'Arco', 'Pantalla', 7),
(989, '¿Cual es el fruto del olivo?', 'Aceituna', 'Cereza', 'Bellota', 'Ninguna es correcta', 7),
(990, 'Planta comestible con tallos blancos y hojas verdes', 'Alcachofa', 'Esparrago', 'Puerro', 'Zanahoria', 7),
(991, 'Las plantas son seres vivos y necesitan para vivir', 'Todas son correctas', 'Agua', 'Luz', 'Aire', 7),
(992, '¿Cuanto viven las plantas efimeras?', 'Una temporada', '100 años', 'Para siempre', '100 - 120  dias', 7),
(993, '¿Cuanto viven las plantas perennes?', 'Varios años', 'Una temporada', 'Para siempre', '100 - 120  dias', 7),
(994, 'Las plantas fabrican su propio alimento y este proceso se llama', 'Fotosíntesis', 'Fotocínstecis', 'Fotomatón', 'Fotogenesis', 7),
(995, 'Para realizar la fotosintesis las plantas necesitan aire, agua, sol y  ..', 'Sales minerales', 'Otras plantas', 'Hidrogeno', 'Ninguna es correcta', 7),
(996, '¿Como se llama la union de agua, minerales y dioxido de carbono?', 'Savia bruta', 'Savia elaborada', 'Nectar', 'Ninguna es correcta', 7),
(997, 'Sabia bruta junto con clorofila y rayos del sol se llama', 'Sabia elavorada', 'Sabia bruta', 'Nectar', 'Ninguna es correcta', 7),
(998, '¿La sabia bruta le sirve a las plantas como alimento?', 'Falso', 'Verdadero', 'A algunas si', 'Solo a los pinos', 7),
(999, 'La sabia elavorada se distribuye por la planta a traves de ...', 'Vaos liberianos', 'Vasos leñosos', 'Vasos sanguineos', 'Clorofila', 7),
(1000, 'La sabia bruta llega a las hojas a traves de ...', 'Vasos leñosos', 'Vaos liberianos', 'Vasos sanguineos', 'Clorofila', 7),
(1001, '¿Como se llama la union de una parte de una planta a otra?', 'Injerto', 'Esqueje', 'Acodo', 'Implante', 7),
(1002, '¿Que parte de la planta recoje el dioxido de carbono?', 'Hojas', 'Tallo', 'Raiz', 'Corteza', 7),
(1003, '¿Que gas expulsan las plantas cuando realizan la fotosintesis?', 'Oxigeno', 'Dioxido de carbono', 'Hidrogeno', 'Helio', 7),
(1004, '¿Como se le llama al conjunto de hojas verdes de una planta?', 'Caliz', 'Corona', 'Corola', 'Arco', 7),
(1005, 'Las plantas estan formadas por', 'Celulas vegetales', 'Celulas animales', 'Celulas procariotas', 'Todas son correctas', 7),
(1006, '¿Cual de las siguientes funciones no pertenecen a la raiz?', 'Absorber luz', 'Absorber agua', 'Absorber sales minerales', 'Todas son correctas', 7),
(1007, '¿Donde se realiza el intercambio de gases en las plantas?', 'Estomas', 'Vasos leñosos', 'Mitocondrias', 'Cloroplastos', 7),
(1008, '¿Donde estan los granos de polen?', 'La antera', 'El pisltilo', 'El filamento', 'El caliz', 7),
(1009, '¿Los ovulos de las plantas estan dentro de?', 'El pistilo', 'La antera', 'El filamento', 'El caliz', 7),
(1010, 'La polinizacion de las plantas puede producirse por', 'Todas son correctas', 'Animales ', 'Viento', 'Agua', 7),
(1011, '¿Donde producen su alimento las plantas?', 'Hojas', 'Raices', 'Tallo', 'Ramas', 7),
(1012, 'Para reproducirse las plantas lo realizan mediante las', 'Semillas', 'Hojas', 'Espinas', 'Raices', 7),
(1013, 'Las plantas producen las semillas dentro de las', 'Flores', 'Espinas', 'Raices', 'Ramas', 7),
(1014, 'Algunas plantas poseen espinas y hojas venenosas que usan como', 'Proteccion', 'Alimentacion', 'Reproduccion', 'Diversion', 7),
(1015, '¿Como se llama la parte de la botanica que estudia los hongos?', 'Micologia', 'Mitologia', 'Botanica', 'Fungicida', 8),
(1016, '¿A que reino pertenecen los hongos y las setas?', 'Reino fungi', 'Reino vegetal', 'Reino animal', 'Reino protista', 8),
(1017, '¿Es lo mismo hongo que seta?', 'No', 'Si ', 'Depende del pais', 'Algunas especies si', 8),
(1018, 'Antes de clasificarse en el reino fungi, fueron clasificados en el', 'Reino vegetal', 'Reino animal', 'Reino protista', 'Reino bacteria', 8),
(1019, 'La parte vegetativa del hongo, que encontramos bajo el suelo se llama', 'Micelio', 'Trufa', 'Basidio', 'Todas son correctas', 8),
(1020, 'La relacion entre diferentes organismos para desarrollarse se llama', 'Simbiosis', 'Saprofitismo', 'Parasitismo', 'Coprofilo', 8),
(1021, 'En el emisferio norte las setas abundan en', 'Otoño', 'Verano', 'Invierno', 'Primavera', 8),
(1022, 'En que epoca del año encontramos el micelio', 'Todo el año', 'Verano', 'Otoño', 'Invierno', 8),
(1023, 'El micelio esta formado por una serie de filamentos que se denominan', 'Hifas', 'Pileos', 'Laminillas', 'Tallos', 8),
(1024, '¿Como es la nutricion de los hongos?', 'Heterotrofa', 'Autotrofa', 'Protista', 'Primaria', 8),
(1025, '¿Cual de estas setas es un lactarius?', 'Rebollon', 'Seta de olmo', 'Robezuelo', 'Amanita cesarea', 8),
(1026, '¿Que producen las setas?', 'Esporas', 'Hifas', 'Micelio', 'Sombrerillo', 8),
(1027, '¿Que hongos son especialmente sensibles a la contaminacion?', 'Liquenes', 'Levaduras', 'Setas', 'Mohos', 8),
(1028, '¿A que reino pertencen los organismos que elaboran la cerveza?', 'Hongos', 'Setas', 'Levaduras', 'Morenas', 8),
(1029, 'Los hongos son heterotrofos y pueden ser ', 'Todas son correctas', 'Simbióticos', 'Parásitos', 'Saprófitos', 8),
(1030, '¿De que se alimetan los hongos?', 'Materia organica', 'Bacterias', 'Agua', 'Helio', 8),
(1031, '¿Que tienen las celulas de los hongos y la de los animales no?', 'Pared celular', 'Nucelo', 'Vacuolas', 'Mitocondrias', 8),
(1032, '¿Los hongos son eucariotas, heterotrofos y unicelulares?', 'Verdadero', 'Falso', 'Solo algunos', 'Solo los liquenes', 8),
(1033, '¿Que favorece la aparicion de los hongos?', 'Humedad', 'Dioxido de carbono', 'Azufre', 'Cloro', 8),
(1034, 'Los hongos unicelularas son', 'Levaduras', 'Setas', 'Hongos', 'Mohos', 8),
(1035, 'Los hongos al igual que las plantas no pueden', 'Desplazarse', 'Crecer', 'Nutrirse', 'Todas son correctas', 8),
(1036, '¿Los hongos son de tipo?', 'Unicelular y pluricelular', 'Venenoso', 'Carnivoros', 'Todas son correctas', 8),
(1037, 'Los hongos pluricelulares son', 'Setas y mohos', 'Levaduras', 'Levaduras', 'Micelios', 8),
(1038, 'Los liquenes son una asociacion entre', 'Hongo y alga', 'Seta y alga', 'Micelo y alga', 'Ninguna es correcta', 8),
(1039, 'Las setas son la parte del hongo que interviene en su', 'Reproduccion', 'Alimentacion', 'Fotosintetis', 'Todas son correctas', 8),
(1040, 'Las setas son hongos ...', 'Pluricelulares', 'Unicelulares', 'Enfermos', 'Todas son correctas', 8),
(1041, 'Los hongos crecen en cualquier lugar y cualquier condicion', 'Falso', 'Verdadero', 'Solo los liquenes', 'Solo las levaduras', 8),
(1042, '¿Los hongos pluricelulares pueden fijarse en el suelo?', 'Verdadero', 'Falso', 'Solo los mohos', 'Solo las setas venenosas', 8),
(1043, 'La amanita muscaria es una seta', 'Aluncinogena', 'Mortal', 'Comestible', 'Venenosa si esta cruda', 8),
(1044, 'Señala la caracteristica incorrecta de los hongos', 'Se desplazan', 'Se alimentan', 'Crecen', 'Producen esporas', 8),
(1045, 'Las partes mas importantes de las setas son', 'Pie y sombrero', 'Falda y gorro', 'Mano y pie', 'Todas son correctas', 8),
(1046, 'Las levaduras producen setas', 'Falso', 'Verdadero', 'Solo algunas', 'Solo las comestibles', 8),
(1047, 'La mayoria de las setas salen en otoño, pero algunas pueden salir', 'Primavera', 'Verano', 'Invierno', 'Todas son correctas', 8),
(1048, '¿Cual de estos hongos es un parasito?', 'Pie de atleta', 'Penicilina', 'Setas', 'Mohos', 8),
(1049, '¿De que hongo se extrae la penicilina?', 'Levadura ', 'Seta', 'Moho', 'Todas son correctas', 8),
(1050, 'Los hongos parasitos de alimentan de materia ', 'Organica viva', 'Organica muerta', 'Inorganica', 'Todas son correctas', 8),
(1051, '¿Los hongos son grandes recicladores?', 'Verdadero', 'Falso', 'Solo las levaduras', 'Solo las setas', 8),
(1052, 'Por su forma de alimentacion los hongos se clasifican en', 'Saprofitos', 'Magníficos', 'Micelios', 'Liquenes', 8),
(1053, '¿Cual no es un hongo?', 'Todas son correctas', 'Algas', 'Bacterias', 'Protozoos', 8),
(1054, '¿En que dos tipos se dividen las setas con hongos?', 'Setas y esporas', 'Setas y espinas', 'Esporas y hongos', 'Setas y hongos', 8),
(1055, '¿Algunos hongos pueden ser?', 'Todas son correctas', 'Venenosos', 'Comestibles', 'Aluninogenos', 8),
(1056, '¿Cual es la funcion del alga en un liquen?', 'Aporta nutrientes', 'La defensa', 'Apora calor', 'Aporta impermeabilidad', 8),
(1057, '¿Cual es la funcion del hongo en un liquen?', 'La fotosintesis', 'La proteccion', 'Aportar calor', 'Aporta impermeabilidad', 8),
(1058, '¿Cual es un hongo?', 'Levadura', 'Fagocito', 'Anguila', 'Ninguna es correcta', 8),
(1059, '¿Que se encuentra en la parte inferior de la seta?', 'Las laminas', 'El veneno', 'Los nutrientes', 'El micelio', 8),
(1060, 'Las esporas de la seta se producen en', 'Las laminas', 'El pie', 'El micelio', 'El sombrero', 8),
(1061, 'Los niscalos crecen en biomas de ', 'Pinos', 'Olivos', 'Naranjos', 'Eucaliptos', 8),
(1062, 'Los campiñones crecen en el campo donde hay', 'Ovejas', 'Lobos', 'Linces', 'Osos', 8),
(1063, 'Cual de estas amanitas es comestible', 'Amanita cesarea', 'Amanita muscaria', 'Amanita phalloides', 'Amanita verna', 8),
(1064, 'Algunas setas tienen en el tallo', 'Un anillo', 'Espinas', 'Ramificaciones', 'Esporas', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_cuestionarios`
--

CREATE TABLE `preguntas_cuestionarios` (
  `id_pregunta` int(10) NOT NULL,
  `id_cuestionario` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `preguntas_cuestionarios`
--

INSERT INTO `preguntas_cuestionarios` (`id_pregunta`, `id_cuestionario`) VALUES
(613, 1),
(614, 1),
(617, 1),
(623, 1),
(623, 12),
(630, 1),
(634, 1),
(638, 1),
(642, 1),
(644, 1),
(658, 1),
(659, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_partida`
--

CREATE TABLE `preguntas_partida` (
  `id` int(10) NOT NULL,
  `id_pregunta` int(10) DEFAULT NULL,
  `id_partida` int(10) DEFAULT NULL,
  `acertada` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(10) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `contrasena` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `contrasena`, `email`, `telefono`, `tipo`) VALUES
(7, 'fernando', 'a75ef907369f33d2dc2be71084ce422ecd2dd1ffb269780449e778237a20ff76', 'fernandoesmr@gmail.com', '652265838', 'user'),
(8, 'administrador', 'da23890e111536e631be13a069ebc5432c9cf28cdbc5deb2a70770ec9597db6d', 'admin@gmail.com', '333333333', 'admin'),
(9, 'prueba', 'a75ef907369f33d2dc2be71084ce422ecd2dd1ffb269780449e778237a20ff76', 'emailprueba@gmail.com', '222222222', 'user');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `preguntas_cuestionarios`
--
ALTER TABLE `preguntas_cuestionarios`
  ADD PRIMARY KEY (`id_pregunta`,`id_cuestionario`),
  ADD KEY `id_cuestionario` (`id_cuestionario`);

--
-- Indices de la tabla `preguntas_partida`
--
ALTER TABLE `preguntas_partida`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pregunta` (`id_pregunta`),
  ADD KEY `id_partida` (`id_partida`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1195;

--
-- AUTO_INCREMENT de la tabla `preguntas_partida`
--
ALTER TABLE `preguntas_partida`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=560;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  ADD CONSTRAINT `cuestionarios_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD CONSTRAINT `partidas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `preguntas_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `preguntas_cuestionarios`
--
ALTER TABLE `preguntas_cuestionarios`
  ADD CONSTRAINT `preguntas_cuestionarios_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `preguntas_cuestionarios_ibfk_2` FOREIGN KEY (`id_cuestionario`) REFERENCES `cuestionarios` (`id`) ON DELETE NO ACTION;

--
-- Filtros para la tabla `preguntas_partida`
--
ALTER TABLE `preguntas_partida`
  ADD CONSTRAINT `preguntas_partida_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `preguntas_partida_ibfk_2` FOREIGN KEY (`id_partida`) REFERENCES `partidas` (`id`) ON DELETE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
