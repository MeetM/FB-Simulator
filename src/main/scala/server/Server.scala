import scala.collection.concurrent._
import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._

case class User(uid : Int, name : String)
case class Post(pid : Int, post : String)

// Server store all the data in-memory
// On start-up initialize some number of users, friendlists and posts
object Server{
  
  println("Starting Server...")
  
  // Using ConcurrentHashMaps for storing data in memory to avoid Map wide locks on update,
  // assuming more reads than writes
  // Need to experiment with concurrenyLevel value
  var userMap : Map[Int, User] = new ConcurrentHashMap[Int, User] asScala
  
  // Using immutable Set for now to store FriendList of each user
  // This means FriendList will be cloned and will replace the old list on any update
  var friendListMap : Map[Int, Set[Int]] = new ConcurrentHashMap[Int, Set[Int]] asScala
  
  // Using immutable Set for now to store FriendList of each user
  // This means FriendList will be cloned and will replace the old list on any update
  var postListMap : Map[Int, List[Int]] = new ConcurrentHashMap[Int, List[Int]] asScala
  
  var posts : Map[Int, String] = new ConcurrentHashMap[Int, String] asScala
  
  def numberOfUsers() = userMap.size
  
  // Initialize Users
  for( i <- 1 to 100) {
    userMap += i -> User(i, "User"+i)
    friendListMap += (i -> Set[Int]())
  }
  
  // Create Friend Relations
  for( i <- 1 to 100) {
    for( j <- 1 to 5) {
      val friend = 1 + scala.util.Random.nextInt(100)
      if(friend != i)
        friendListMap.replace(i, friendListMap(i) + friend)
        friendListMap.replace(friend, friendListMap(friend) + i)
    }
  }
  
  
}

