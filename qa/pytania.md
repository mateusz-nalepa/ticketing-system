1. Jak monitorować, że system do monitoringu nie działa?
   Najlepiej mieć 2 systemy? Albo jeden który po prostu monitoruje drugi?
2. Najcześciej spotykane błędy?
3. Dobre patterny?
4. Logi jako JSON do konsoli i niech scraper zbiera.
   - ERRORY do Lokiego i niech lecą Exemplary, żeby mega szybka analiza była
   - a ogólnie wszysto do KIBANY, WDYT?
5. W3CPropagation z tego co widziałem w kodzie, nie ogarnia inteceptor do WebClient'a, RestClient'a, tylko coś związanego z obserwacją, y?
    Jeśli tak, to w sumie czy to dobry pomysł żeby spring miał taki patent, że 1 interceptor śmiga zarówno w restClient i webClient?
6. Jak daleko iść z Trace? Tzn, jak wysyłam event to wysłać też traceId i niech wszystko dalej będzie raportowane? Czy raczej klasycznie "to zależy"? :D 
7. Tak dla pewności, MeterRegistry to tylko metryki, a ObservationRegistry to metryki ale też trace, więc w sumie "to zależy" ale warto się przenieść na observation gdy się mieć tracing, w sensie wiekszy wglad do systemu?
8. Jakie zalecenia odnośnie prowadzenia zajec dla studentow?
9. Tracing a opoznienia sieciowe, warto monitorować? 
10. Jak wykryć błąd, zanim nastąpi? tzn, kumuluje się, ale np. za 10 minut może byc awaria, jakies sprawdzone patterny?
    Jako przyklad, saturacja puli watkow o ktorej rozmawialismy. 
11. Czy zalecasz odpalic management na osobnym porcie? albo osobna pula watkow na endpointy /status, /readiness/ liveness?
    informacyjnie dla Nalepy: https://github.com/spring-projects/spring-framework/issues/35238
12. Nie mogło zabraknąć: AI a Kwestia observabillity, tracingu