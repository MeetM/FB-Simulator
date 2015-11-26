import akka.actor.{ ActorRef, ActorSystem, Props, Actor}
import akka.pattern._
import Worker.GET_FRIENDLIST

// Worker executes the task of reading, writing, filtering data from Server
// and return the output to the RequestHandler

object Worker {
  case class POST_STATUS(uid : Int, status : String)
  case class ADD_FRIEND(uid1 : Int, uid2 : Int)
  case class GET_FRIENDLIST(uid : Int)
}

class Worker extends Actor {
  import Worker._
  
  def receive = {
    case GET_FRIENDLIST(uid) => {
      val flist : Set[Int] = Server.friendListMap(uid)
      var op : StringBuffer = new StringBuffer
      op.append("{")
      op.append("'uid' : "+uid+",")
      op.append("'friendList' : [")
      for(fid <- flist){
        op.append(fid+",")
      }
      op.append("]}")
      println(op)
    }
  }
}