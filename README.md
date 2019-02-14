# cljs-add-script

[![Clojars Project](https://img.shields.io/clojars/v/cljs-add-script.svg)](https://clojars.org/cljs-add-script)

A ClojureScript library designed to dynamically add \<script\> tag to html DOM using https://github.com/plumatic/dommy

## Usage

```clojure
(ns app.core
  (:require [cljs-add-script.core :refer [add-script!]]))

; callback function 
;; login is written somewhere else
(defn ^:export onTelegramAuth [user]
  (let [username (.-username user)
        id (.-id user)]
    (login username)))
    

(add-script! "https://telegram.org/js/telegram-widget.js?5"
    (fn [e]
      (println "Telegram login script loaded"))
    {:async               "async"
     :data-telegram-login ""
     :data-size           "medium"
     :data-userpic        "false"
     :data-onauth         "app.core.onTelegramAuth(user)"
     :data-radius         "5"
     :parent-sel          :#tg-login-fieldset})
```
where 
- :parent-sel dommy selector of parent element

which produces

```html
<script type="text/javascript" async="async" data-telegram-login="" 
    data-size="medium" data-userpic="false" 
    data-onauth="app.core.onTelegramAuth(user)" data-radius="5" 
    src="https://telegram.org/js/telegram-widget.js?5"></script>
```



## License

Copyright © 2019 Vladislav Shishkov

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
