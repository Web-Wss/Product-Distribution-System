const storage = {
  set(key: string, value: any) {
    localStorage.setItem(key, JSON.stringify(value))
  },
  get(key: string) {
    return JSON.parse(localStorage.getItem(key) as string)
  },
  remove(key: string) {
    localStorage.removeItem(key)
  },

  getKeys(){
    let items = this.getAll();
    let keys = [];
    for(let i = 0; i < items.length; i++){
      keys.push(items[i].key);
    }
    return keys;
  },

  getLength(){
    return localStorage.length;
  },

  removeAll(){
    localStorage.clear();
  },

  getAll(){
    let len = localStorage.length;
    let arr = new Array();
    for(let i = 0; i < len; i++){
      let key = localStorage.key(i);
      let value = localStorage.getItem(key as string);
      arr.push({
        key: key,
        value: value
      })
    }
    return arr;
  }
}

export default storage