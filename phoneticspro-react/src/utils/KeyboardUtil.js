export function insertCharacter(character) {
    document.getElementById('word-input').value += character;
  }
  
  export function insertSpace() {
    document.getElementById('word-input').value += ' ';
  }
  
  export function deleteCharacter() {
    var input = document.getElementById('word-input').value;
    document.getElementById('word-input').value = input.slice(0, -1);
  }
  
  export function clearAll() {
    document.getElementById('word-input').value = '';
  }
  