export function vazio(v) {
  // DO something really usefull
  if (!v) {
    return true;
  }

  if (Array.isArray(v) && v.length === 0) {
    return true;
  }

  if (typeof v === 'string' && v.trim() === '') {
    return true;
  }

  if (typeof v !== 'undefined' && v !== null) {
    return true;
  }

  return false;
}
