# Relazione progetto - CryptoTracker Mini

## 1. Scopo del progetto

CryptoTracker Mini è un'app Android nativa sviluppata in Kotlin con Jetpack Compose. L'obiettivo è permettere all'utente di visualizzare una lista di criptovalute, consultarne il dettaglio e salvare le monete preferite in locale.

## 2. Funzionalità principali

- Visualizzazione delle principali criptovalute tramite API remota CoinGecko.
- Dettaglio di una criptovaluta tramite seconda chiamata API.
- Salvataggio e rimozione dai preferiti.
- Visualizzazione dei preferiti salvati localmente.
- Navigazione tra Home, Dettaglio e Preferiti.
- Gestione di loading, errori e stati vuoti.
- Ricerca locale delle criptovalute nella Home.
- Aggiornamento manuale dei dati tramite pulsante dedicato.
- Cambio lingua italiano/inglese con salvataggio della preferenza.

## 3. Struttura dei sorgenti

Il progetto usa un unico modulo Android, ma il codice è separato in package secondo il principio di separation of concerns:

- `ui`: schermate Compose, componenti grafici, navigazione, tema, stringhe UI e ViewModel.
- `domain`: modelli puliti, interfacce repository e use case.
- `data`: Retrofit, DTO, Room, entity, DAO, mapper e implementazione repository.
- `di`: creazione manuale delle dipendenze tramite `AppContainer`.

## 4. Architettura

Il progetto segue una struttura ispirata a MVVM e Clean Architecture:

```text
Compose UI -> ViewModel -> UseCase -> Repository interface -> Repository implementation -> Retrofit / Room
```

La UI non comunica direttamente con Retrofit o Room. Le schermate osservano lo stato esposto dai ViewModel tramite `StateFlow`, mentre la logica applicativa passa dagli use case e dal repository.

## 5. API remote

Sono usate due chiamate remote distinte:

1. Lista crypto: recupera le principali criptovalute ordinate per market cap tramite `/coins/markets`.
2. Dettaglio crypto: recupera informazioni specifiche su una singola moneta tramite `/coins/{id}`.

Le API vengono chiamate con Retrofit. Le risposte vengono rappresentate tramite DTO e convertite in modelli domain tramite `CryptoMapper`.

## 6. Coroutines e gestione thread

Le operazioni remote e locali vengono eseguite con Kotlin Coroutines. Nei ViewModel viene usato `viewModelScope.launch`, mentre le operazioni lente vengono eseguite su `Dispatchers.IO`, evitando di bloccare il Main Thread.

## 7. Storage locale

Il requisito opzionale di salvataggio locale è soddisfatto usando Room. Le criptovalute preferite vengono salvate nella tabella `favorite_cryptos` e osservate tramite `Flow`, così la schermata Preferiti può aggiornarsi quando il database locale cambia.

## 8. Localizzazione italiano/inglese

Il progetto include una gestione semplice della lingua dell'interfaccia. Nella Home sono presenti due comandi, IT ed EN, che permettono di cambiare dinamicamente i testi principali dell'app tra italiano e inglese.

La lingua è gestita tramite `AppLanguage` e `AppStrings`, mentre la preferenza scelta dall'utente viene salvata tramite `SharedPreferences`, così viene mantenuta anche ai successivi avvii dell'app.

## 9. Punti di forza

- Progetto piccolo ma completo.
- Architettura separata.
- Uso di Compose, ViewModel, StateFlow, Coroutines, Retrofit e Room.
- Gestione degli errori, degli stati vuoti e del caricamento.
- Ricerca locale e aggiornamento manuale dei dati.
- Codice organizzato in modo coerente.

## 10. Possibili migliorie

- Aggiunta di WorkManager per aggiornare periodicamente i prezzi dei preferiti.
- Aggiunta di grafici storici.
- Aggiunta di filtri più avanzati.
- Aggiunta di DataStore al posto di SharedPreferences.
- Aggiunta di test più completi su repository, use case e ViewModel.
