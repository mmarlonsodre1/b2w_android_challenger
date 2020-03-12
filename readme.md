#B2W Challenger - Android

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d6c2fd7fb0bb44778165e4cc7c670e48)](https://app.codacy.com/manual/mmarlonsodre1/b2w_android_challenger?utm_source=github.com&utm_medium=referral&utm_content=mmarlonsodre1/b2w_android_challenger&utm_campaign=Badge_Grade_Settings)

## Tecnologias Utilizadas
*   Retrofit2
*   GSON
*   RxJava
*   Navigation Component
*   LiveData
*   Shared Preferences
*   Dagger2
*   Picasso
*   Shared Element Transition
*   Endless Scrolling

## Detalhes Técnicos
*   Target Sdk Version 29
*   Min Sdk Version 21
*   Dispositivos: Smartphone Android
*   Arquitetura MVP

## Pré-requisitos
*   Android SDK 29
*   Android Build Tools v29.0.2
*   Gradle 5.4.1
*   Android Studio 3.5.1
*   Java 1.8

## Instalando
Abra o projeto na pasta raiz pelo android studio, a build padrão do gradle será executada automaticamente.
Após terminar a build do gradle, seleciona a aba "Build Variants" e selecione o ambiente desejado, e então clique para rodar o projeto.

*   build.gradle : responsável por configurações base do projeto, agrega arquivos de funções extras ao gradle
*   app/build.gradle : responsável por configurações do aplicativo, possui definições de versão, configuração de flavors, e importação de bibliotecas

### Ambientes disponíveis
*   debug (desenvolvimento)
*   release (Versão de produção)