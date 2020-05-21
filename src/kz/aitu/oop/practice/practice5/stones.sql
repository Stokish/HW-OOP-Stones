-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Май 21 2020 г., 06:19
-- Версия сервера: 8.0.18
-- Версия PHP: 7.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `stones`
--

-- --------------------------------------------------------

--
-- Структура таблицы `stone`
--

CREATE TABLE `stone` (
  `stone_id` int(11) NOT NULL,
  `stone_name` varchar(35) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stone_weight` double NOT NULL,
  `stone_cost` double NOT NULL,
  `stone_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `stone`
--

INSERT INTO `stone` (`stone_id`, `stone_name`, `stone_weight`, `stone_cost`, `stone_type_id`) VALUES
(1, 'Diamond', 0.2, 4000, 1),
(2, 'Sapphire', 0.05, 8000, 1),
(3, 'Ruby', 0.2, 1000000, 1),
(4, 'Emerald', 0.75, 525, 1),
(5, 'Aquamarine', 0.04, 100, 2),
(6, 'Garnet', 0.25, 1400, 2),
(7, 'Opal', 0.02, 1100, 2),
(8, 'Tourmaline', 0.04, 1000, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `stone_type`
--

CREATE TABLE `stone_type` (
  `stone_type_id` int(11) NOT NULL,
  `stone_type_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `stone_type`
--

INSERT INTO `stone_type` (`stone_type_id`, `stone_type_name`) VALUES
(1, 'precious'),
(2, 'semi-precious');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `stone`
--
ALTER TABLE `stone`
  ADD PRIMARY KEY (`stone_id`),
  ADD KEY `stone_type_id` (`stone_type_id`);

--
-- Индексы таблицы `stone_type`
--
ALTER TABLE `stone_type`
  ADD PRIMARY KEY (`stone_type_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `stone`
--
ALTER TABLE `stone`
  MODIFY `stone_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `stone`
--
ALTER TABLE `stone`
  ADD CONSTRAINT `stone_ibfk_1` FOREIGN KEY (`stone_type_id`) REFERENCES `stone_type` (`stone_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
