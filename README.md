# ypcat-auth

A Clojure library designed to authenticate with NIS by local "ypcat"
command.


## Usage

'''
(require '[ypcat-auth.core :refer :all])
(ypcat-passwd-authenticated? "username" "password")
'''

## License

Copyright © 2014 Kazuo Koga

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
