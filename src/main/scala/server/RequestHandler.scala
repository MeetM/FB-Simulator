import akka.actor.{ ActorRef, ActorSystem, Props, Actor}
import akka.pattern._
import scala.util.Random

object RequestHandler extends App{
  val system = ActorSystem("FB-SIM")
  for(i <- 1 to 10) {
    val uid = 1+Random.nextInt(78)
    val worker = system.actorOf(Props[Worker])
    worker ! Worker.GET_FRIENDLIST(uid)
  }
}