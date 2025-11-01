


### Get logs from LOKI directly

```shell
curl -G http://localhost:3100/loki/api/v1/query_range \
  --data-urlencode 'query={application=~".+"}' \
  --data-urlencode 'start='$(date -u -d '10 minutes ago' +%s%N) \
  --data-urlencode 'end='$(date -u +%s%N) \
  --data-urlencode 'limit=100' | jq .
```

### Send trace to TEMPO

```shell
curl -X POST http://localhost:9411/api/v2/spans -H "Content-Type: application/json" -d '[]'
```