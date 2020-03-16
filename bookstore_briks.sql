-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 16 2020 г., 20:20
-- Версия сервера: 10.4.8-MariaDB
-- Версия PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `bookstore_briks`
--

-- --------------------------------------------------------

--
-- Структура таблицы `books`
--

CREATE TABLE `books` (
  `title` varchar(50) NOT NULL,
  `author` varchar(150) NOT NULL,
  `pages` int(3) NOT NULL,
  `year` int(4) NOT NULL,
  `genre` varchar(30) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `qty` int(3) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`title`, `author`, `pages`, `year`, `genre`, `price`, `qty`, `description`) VALUES
('Kapital', 'Karl Marx', 123, 1294, 'TRUE', '99.99', 10, 'BEST BOOK IN UNIVERSE YES'),
('Mein Kampf', 'Adlof Hitler', 12, 1929, 'Biography', '12.90', 31, 'adfsd');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_login` varchar(30) NOT NULL,
  `book_title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`id`, `user_login`, `book_title`) VALUES
(389, 'user', 'Kapital'),
(2036, 'gomer', 'Mein Kampf'),
(8061, 'user', 'Mein Kampf');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `is_admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`login`, `password`, `is_admin`) VALUES
('admin', 'admin', 1),
('gomer', '234', 0),
('jason', '123', 0),
('user', '123', 0),
('user user', '123 123', 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`title`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_title` (`book_title`),
  ADD KEY `user_login` (`user_login`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`book_title`) REFERENCES `books` (`title`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_login`) REFERENCES `users` (`login`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
