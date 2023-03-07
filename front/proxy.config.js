const PROXY_CONFIG = [
  {
    context: ['/api'],
    target: 'http://localhost:9090',
    secure: false,
    loglevel: 'debug'
  },
  {
    context: ['/auth'],
    target: 'http://localhost:8080',
    secure: false,
    loglevel: 'debug'
  }
];
module.exports = PROXY_CONFIG;
