package com.challenge.liter.alura.service;

interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
