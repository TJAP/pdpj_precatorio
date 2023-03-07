(function (window) {
  window.__env = window.__env || {};
  window.__env.production = false;
  window.__env.nome = "Configuração Web";
  window.__env.descricao = "Configuração Web - Front-End do Módulo de Configuração";
  window.__env.apiUrl = "http://localhost:9091/api/v1"; //<-Backend/endpoint
  window.__env.ssoUrl = "http://localhost:8180/auth/"; //<-Keycloak/auth/
  //window.__env.ssoUrl = "https://sso.stg.cloud.pje.jus.br/auth/"; //<-Keycloak/auth/
  window.__env.realm = "pje"; //<-Keycloak realms
  window.__env.clientId = "precatorio"; //<-Keycloak clint_id
  window.__env.redirectUri = "http://localhost:4200/cadastro/novo"; //<-Fron-End/
  window.__env.apiTucujuris = "http://localhost:8081/api";
  // window.__env.apiTucujuris = "https://testservices.tjap.jus.br/back/api";
}(this));
