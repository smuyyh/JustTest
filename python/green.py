import datetime
import os
import random

# from heavy import special_commit


def modify():
    file = open('../java/xx.txt', 'r')
    file.close()
    file = open('../java/xx.txt', 'w+')
    file.write(chr(random.randint(0, 100)))
    file.close()


def commit():
    os.system('git commit -a -m "commit" > /dev/null 2>&1')


def set_sys_time(year, month, day):
    os.system('date %02d%02d1213%04d' % (month, day, year))


def trick_commit(year, month, day):
    set_sys_time(year, month, day)
    modify()
    commit()


def daily_commit(start_date, end_date):
    for i in range((end_date - start_date).days + 1):
        cur_date = start_date + datetime.timedelta(days=i)
        trick_commit(cur_date.year, cur_date.month, cur_date.day)


if __name__ == '__main__':
    daily_commit(datetime.date(2016, 3, 23), datetime.date(2016, 3, 25))