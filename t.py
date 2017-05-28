from asciipic.db import oracle
DB = oracle.ORACLE_DB
U = DB.User
S = DB.session
from asciipic.common import tools as t
pas = t.hash_password("maria")
u1 = U(username="matei1", password="maria", email="mata suge", salt="1")
u2 = U(username="matei2", password="maria", email="mata suge", salt="1")
u3 = U(username="matei3", password="maria", email="mata suge", salt="1")
u4 = U(username="matei4", password="maria", email="mata suge", salt="1")
S.add(u1)
S.add(u2)
S.add(u3)
S.add(u4)
S.commit()
S = DB.session
u = S.query(U).filter(U.username.like("matei1"))

